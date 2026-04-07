import csv
import os
import matplotlib.pyplot as plt

category_totals = {}

def show_summary():
    global category_totals
    category_totals.clear()
    filename = 'expenses.csv'
    if not os.path.exists(filename):
        return "No expenses recorded yet."
    with open(filename, mode='r') as file:
        reader = csv.reader(file)
        next(reader)
        for row in reader:
            category=row[1]
            amount=float(row[2])
            if category in category_totals:
                category_totals[category]+=amount
            else:
                category_totals[category]=amount
    highest_category=max(category_totals, key=category_totals.get)
    highest_amount=category_totals[highest_category]
    print(f"Highest Spending Category: {highest_category} (${highest_amount:.2f})")
    print(f"Full Breakdown: {category_totals}")
    return "Summary Of the Graphs Generated Successfully"

def pie_chart():
    if not category_totals:
        return "No transactions."
    labels=list(category_totals.keys())
    sizes=list(category_totals.values())
    plt.figure(figsize=(6, 6))
    plt.pie(sizes, labels=labels, autopct='%1.1f%%', startangle=140)
    plt.title("Expense Breakdown by Category")
    plt.show()

def monthly_summary(t_month):
    filename = 'expenses.csv'
    if not os.path.exists(filename):
        return "No expenses recorded yet."
    mnt_ct_total= {}
    with open(filename, mode='r') as file:
        reader = csv.reader(file)
        next(reader)
        for row in reader:
            date=row[0]
            category=row[1]
            amount=float(row[2])
            if date.startswith(t_month):
                    if category in mnt_ct_total:
                        mnt_ct_total[category] += amount
                    else:
                        mnt_ct_total[category] = amount
    if not mnt_ct_total:
        return f"No data found for {t_month}."
    labels=list(mnt_ct_total.keys())
    sizes=list(mnt_ct_total.values())
    plt.figure(figsize=(6, 6))
    plt.pie(sizes, labels=labels, autopct='%1.1f%%', startangle=140)
    plt.title(f"Expense Distribution - {t_month}")
    plt.show()
    summary_text=f"Summary for {t_month}:\n"
    for cat,amt in mnt_ct_total.items():
        summary_text+=f"- {cat}: ${amt:.2f}\n"
    return summary_text
    