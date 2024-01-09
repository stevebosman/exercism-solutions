#[derive(PartialEq)]
enum State {
    Separator,
    Uppercase,
    Lowercase,
}

fn get_char_state(c: &char) -> State {
    if c.is_uppercase() {
        State::Uppercase
    } else if c.is_lowercase() {
        State::Lowercase
    } else {
        State::Separator
    }
}

pub fn abbreviate(phrase: &str) -> String {
    let mut string = String::new();
    let mut last_char_type = State::Separator;
    
    for c in phrase.chars() { 
        if c == '\'' {
            continue;
        }
        let curr_char_type:State = get_char_state(&c);

        if last_char_type == State::Separator || (
            last_char_type == State::Lowercase && curr_char_type == State::Uppercase
        ) {
            if curr_char_type != State::Separator {
                for u in c.to_uppercase() {
                    string.push(u);
                }
            }
        }
        last_char_type = curr_char_type;
    }
    string
}
