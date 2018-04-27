/*
 *Shinya Aoi
 * 04/26/2018
 * CSS143 HW privacy leaks
 */

/**
 * This class represents a bill or outstanding. Each instance variables makes
 * a copy of it to avoid privacy leaks. String is immutable.
 */
public class Bill {
    private Money amount;
    private Date dueDate;
    private Date paidDate;
    private String originator;

    /**
     * Constructor that takes objects such as amount, dueDate, and originator.
     * Making a copy of data to prevent privacy leaks.
     * @param amount - an object from Money class.
     * @param dueDate - an object from Date
     * @param originator
     */
    public Bill(Money amount, Date dueDate, String originator){
        this.amount = new Money(amount);
        this.dueDate = new Date(dueDate);
        this.paidDate = null;
        this.originator = originator;
    }

    /**
     * Copy constructor that copies values of toCopy.
     * Making a copy to avoid privacy leaks. If paidDate is null,
     * it will return null.
     * @param toCopy - an object
     */
    public Bill(Bill toCopy){
        this.amount = new Money(toCopy.amount);
        this.dueDate = new Date(toCopy.dueDate);
        if (toCopy.paidDate!=null) {
            this.paidDate = new Date(toCopy.paidDate);
        }
        this.originator = toCopy.originator;
    }


    /**
     * This returns a copy of amount of Money.
     * @return a copy of amount
     */
    public Money getAmount(){
        return new Money(amount);
    }

    /**
     * This returns a copy of dueDate of Date.
     * @return a copy of dueDate
     */
    public Date getDueDate(){
        return new Date(dueDate);
    }

    /**
     * This returns originator.
     * @return a string value of originator
     */
    public String getOriginator(){
        return originator;
    }

    /**
     * This method checks if the bill is paid.
     * @return true if it is paid which is not null.
     */
    public boolean isPaid(){
        return paidDate != null;
    }

    /**
     * This method sets a date when a bill is paid. If datePaid
     * is before the due date, update the paidDate and return tru.
     * Otherwise, return false.
     * @param datePaid
     * @return false if datePaid is after dueDate.
     */
    public boolean setPaid(Date datePaid){
        if(paidDate!=null||!dueDate.isAfter(datePaid)){
            return false;
        }
        paidDate = new Date(datePaid);
        return true;
    }

    /**
     * This sets a due date. If the bill is already paid, this returns false.
     * If not, update the dueDate and returns true.
     * @param nextDate
     * @return true if the bill is not paid yet.
     */
    public boolean setDueDate(Date nextDate){
        if(isPaid()||nextDate==null) return false;
        else{
            dueDate = new Date(nextDate);
        }
        return true;
    }

    /**
     * This method sets an amount. If it is already paid, it returns false.
     * If not, change the amount and returns true.
     * @param amount
     * @return true, if it is not paid.
     */
    public boolean setAmount(Money amount){
        if(isPaid()||amount==null) return false;
        else{
            this.amount = new Money(amount);
        }
        return true;
    }

    /**
     * This method sets an originator.
     * @param originator
     */
    public void setOriginator(String originator){
        this.originator = originator;
    }

    /**
     * Override to print out the details of a bill.
     * @return amount, dueDate, originator, and paidDate.
     */
    @Override
    public String toString() {
        String retVal = "";
        if(isPaid()){
            retVal = "Amount is:"+amount+", Due date:"+dueDate+", To: "+originator+", Paid date:"+paidDate;
        }
        else if(paidDate==null){
            retVal = "Amount is:"+amount+", Due date:"+dueDate+", To: "+originator+", You have not paid yet!";
        }
        return retVal;
    }

    /**
     * Override equals method. Check only.
     * @param toCompare change it to that
     * @return true if they are the same.
     */
    @Override
    public boolean equals(Object toCompare){
        if(!(toCompare instanceof Bill)) return false;
        Bill that = (Bill) toCompare;
        return this.amount.equals(that.amount) && this.dueDate.equals(that.dueDate) && this.originator.equals(that.originator);
    }
}
