class RunLengthEncoding {

    private static encodeCount(currentChar, currentCount) {
        def result = ""
        if (currentCount == 1) {
            result = currentChar
        } else if (currentCount > 1) {
            result = currentCount + currentChar
        }
        result
    }

    static encode(input) {
        def result = ""
        def currentChar = ""
        def currentCount = 0
        input.each { c -> 
            if (currentChar != c) {
                result += encodeCount(currentChar, currentCount)
                currentChar = c
                currentCount = 0
            }
            currentCount++
        }
        result += encodeCount(currentChar, currentCount)
        result
    }

    static decode(input) {
        def result = ""
        def encodings = (input =~ /\d*[a-zA-Z ]{1}/).findAll()
        encodings.each { 
            def match1
            if (match1 = it =~ /(\d*)([a-zA-Z ]{1})/) {
                if (match1.group(1) == "") {
                    result += match1.group(2)
                } else {
                    result += match1.group(2) * (match1.group(1) as Integer)
                }
            }
        }
        result
    }
}