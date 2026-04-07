import tkinter as tk
from tkinter import messagebox,simpledialog
from tracker import add_expense
from analytics import show_summary, pie_chart, monthly_summary

root=tk.Tk()
root.title("Smart Expense Tracker ")
root.geometry("400x500")

date_label=tk.Label(root,text="Date (YYYY-MM-DD):")
date_label.pack(pady=5)
date_entry=tk.Entry(root)
date_entry.pack(pady=5)

category_label=tk.Label(root,text="Enter Category:")
category_label.pack(pady=5)
category_entry=tk.Entry(root)
category_entry.pack(pady=5)

amount_label=tk.Label(root,text="Enter Amount:")
amount_label.pack(pady=5)
amount_entry=tk.Entry(root)
amount_entry.pack(pady=5)

description_label=tk.Label(root,text="Enter Description:")
description_label.pack(pady=5)
description_entry=tk.Entry(root)
description_entry.pack(pady=5)

def submit_expense_data():
    date_val=date_entry.get()
    category_val=category_entry.get()
    amount_val=amount_entry.get()
    description_val=description_entry.get()
    result=add_expense(date_val,category_val,amount_val,description_val)
    messagebox.showinfo("Result", result)
    date_entry.delete(0, tk.END)
    category_entry.delete(0, tk.END)
    amount_entry.delete(0, tk.END)
    description_entry.delete(0, tk.END)

def view_total_summary():
    status=show_summary()
    if status == "No expenses recorded yet.":
        messagebox.showinfo("Result", status)
        return
    pie_chart()

def monthly_analysis():
    target_month=simpledialog.askstring("Month Selection", "Enter month to view (YYYY-MM):")
    if target_month:
        result=monthly_summary(target_month)
        messagebox.showinfo("Monthly Summary", result)
monthly_btn=tk.Button(root,text="View Monthly Summary",command=monthly_analysis)
monthly_btn.pack(pady=10)
all_time_btn=tk.Button(root,text="View All-Time Chart",command=view_total_summary)
all_time_btn.pack(pady=10)
save_button=tk.Button(root,text="Save Expense",command=submit_expense_data)
save_button.pack(pady=20)
root.mainloop()
