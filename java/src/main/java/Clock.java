class Clock {
    private int hours;
    private int minutes;

    Clock(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
        fixHoursAndMinutes();
    }

    void add(int minutes) {
        this.minutes += minutes;
        fixHoursAndMinutes();
    }

    private void fixHoursAndMinutes() {
        int hrs = this.hours;
        int mins = this.minutes;
        while (mins < 0) {
            mins += 60;
            hrs -= 1;
        }
        while (hrs < 0) {
            hrs += 24;
        }
        this.hours = (hrs + (mins/60)) % 24 ; 
        this.minutes = mins % 60;    
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hours, minutes);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Clock) && ((Clock)obj).hours == hours && ((Clock)obj).minutes == minutes;
    }

}