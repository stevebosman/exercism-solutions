class AllYourBase {
    private def base10

    AllYourBase(inputBase, digits) {
        if (inputBase < 2) throw new ArithmeticException("inputBase must be greater than 2")
        def total = 0
        digits.each { digit -> 
            if (digit < 0) throw new ArithmeticException("digits must be non-negative")
            if (digit >= inputBase) throw new ArithmeticException("digits must be less than inputBase")
            total = inputBase * total + digit
        }
        this.base10 = total
    }

    def rebase(outputBase) {
        if (outputBase < 2) throw new ArithmeticException("outputBase must be greater than 2")
        def list = []
        def total = base10
        if (total == 0) {
            list = [0]        
        } else {
            while (total > 0) {
                list = [total % outputBase, *list]
                total = total.intdiv(outputBase)
            }
        }
        return list
    }
}