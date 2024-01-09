def response(hey_bob):
    hey_bob = hey_bob.strip()
    
    if len(hey_bob) == 0:
        result = "Fine. Be that way!"
    elif hey_bob.isupper():
        if hey_bob[-1] == "?":
            result = "Calm down, I know what I'm doing!"
        else:
            result = "Whoa, chill out!"
    elif hey_bob[-1] == "?":
        result = "Sure."
    else:
        result = "Whatever."
    return result
