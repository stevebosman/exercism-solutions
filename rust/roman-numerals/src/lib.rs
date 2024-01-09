use std::fmt::{Display, Formatter, Result};

#[derive(Debug)]
#[derive(PartialEq)]
pub struct Roman {
    text: String
}

impl Display for Roman {
    fn fmt(&self, f: &mut Formatter<'_>) -> Result {
        write!(f, "{}", &self.text)
    }
}

impl From<u32> for Roman {
    
    fn from(num: u32) -> Self {
        let mut text = String::new();
        for _i in 0..num/1000 {
            text.push_str("M");
        }
        text = Roman::addTriple(text, (num/100)%10, "C", "D", "M");
        text = Roman::addTriple(text, (num/10)%10, "X", "L", "C");
        text = Roman::addTriple(text, num%10, "I", "V", "X");
            
        Self {
            text
        }
    }
}

impl Roman {
    fn addTriple(mut text: String, val: u32, one: &str, five: &str, ten: &str) -> String {
        if val == 9 {
            text.push_str(one);             
            text.push_str(ten);             
        } else if val >= 5 {
            text.push_str(five);             
            for _i in 0..val-5 {
                text.push_str(one);
            }
        } else if val == 4 {
            text.push_str(one);             
            text.push_str(five);             
        } else if val > 0 {
            for _i in 0..val {
                text.push_str(one);
            }
        }
        text
    }
}