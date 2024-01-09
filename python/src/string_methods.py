"""Functions to help edit essay homework using string manipulation."""
import re

def capitalize_title(title):
    """Convert the first letter of each word in the title to uppercase if needed.

    :param title: str - title string that needs title casing.
    :return: str - title string in title case (first letters capitalized).
    """

    return title.title()


def check_sentence_ending(sentence):
    """Check the ending of the sentence to verify that a period is present.

    :param sentence: str - a sentence to check.
    :return: bool - return True if punctuated correctly with period, False otherwise.
    """

    return sentence.endswith('.')


def clean_up_spacing(sentence):
    """Verify that there isn't any whitespace at the start and end of the sentence.

    :param sentence: str - a sentence to clean of leading and trailing space characters.
    :return: str - a sentence that has been cleaned of leading and trailing space characters.
    """

    return sentence.strip()


def replace_single_word(potential_word, old_word, new_word):
    """Replace a word in the provided sentence with a new one.

    :param potential_word: str - a word potentially with punctuation.
    :param old_word: str - word to replace.
    :param new_word: str - replacement word.
    :return: str - input sentence with new words in place of old words.
    """
    #Searching on a pattern should catch replacements like "man" with "person" when given
    #"man woman human mankind"
    pattern = re.compile(f'^[^a-z]*{old_word}[^a-z]*$')
    if pattern.match(potential_word):
        result = potential_word.replace(old_word, new_word)
    else:
        result = potential_word
    return result

def replace_word_choice(sentence, old_word, new_word):
    """Replace a word in the provided sentence with a new one.

    :param sentence: str - a sentence to replace words in.
    :param old_word: str - word to replace.
    :param new_word: str - replacement word.
    :return: str - input sentence with new words in place of old words.
    """
    words = [replace_single_word(word, old_word, new_word) for word in sentence.split(' ')]
    return ' '.join(words)
