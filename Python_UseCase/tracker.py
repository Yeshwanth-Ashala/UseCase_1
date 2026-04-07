import os
import csv


def add_expense(date,category,amount,description):
    file_name='expenses.csv'
    try:
        amount=float(amount)
        if amount<=0 or not date or not category or not description:
            raise Exception("Invalid Input")
    except Exception:
        return "Enter valid data"
    if not os.path.exists(file_name):
        with open(file_name, mode='w', newline='') as file:
            writer = csv.writer(file)
            writer.writerow(['Date', 'Category', 'Amount', 'Description'])
    with open(file_name, mode='a', newline='') as file:
        writer = csv.writer(file)
        writer.writerow([date, category, amount, description])

    return "Successfully Added"



