use std::fmt;

#[derive(Debug)]
#[derive(PartialEq)]
pub struct Clock {
    minutes: i32
}

impl Clock {
    pub fn new(hours: i32, minutes: i32) -> Self {
        let mut mins = (hours * 60 + minutes) % (60 * 24);
        if mins < 0 {
            mins += 60 * 24;
        }
        
        Clock {
            minutes: mins
        }
    }

    pub fn add_minutes(&self, minutes: i32) -> Self {
        Clock::new(0, self.minutes + minutes)
    }
}

impl fmt::Display for Clock {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        write!(f, "{:02}:{:02}", &self.minutes / 60, &self.minutes % 60)
    }
}
