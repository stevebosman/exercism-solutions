def sum_of_multiples(limit, multiples):
    unique_multiples = set()
    for multiple in multiples:
        unique_multiples = unique_multiples.union(get_multiples(limit, multiple))
    return sum(unique_multiples)

def get_multiples(limit, multiple):
    if multiple == 0:
        return {0}
    return {s for s in range(multiple, limit, multiple)}
