use std::collections::HashSet;

pub fn check(candidate: &str) -> bool {
    let mut chars = HashSet::new();
    let mut result = true;
    for c in candidate.to_lowercase().chars() {
        if c.is_alphabetic() {
            if chars.contains(&c) {
                result = false;
                break;
            }
            chars.insert(c);
        }
    }
    result
}
