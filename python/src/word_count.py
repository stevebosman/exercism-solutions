import re

def clean(sentence):
    return re.sub(
        r"('*[^a-zA-Z0-9']+'*|^'*|'*$)", 
        " ", 
        sentence.lower()
    )

def get_words(sentence):
    return sentence.split()

def count_words(sentence):
    words = get_words(clean(sentence))

    result = {}
    for word in words:
        result[word] = result.get(word, 0) + 1
    return result
