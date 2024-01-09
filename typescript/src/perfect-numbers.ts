export function classify(n: number) {
  const range = function(startAt:number, endAt:number) {
    if (startAt > endAt) throw new Error("startAt must not be larger than endAt.")
    return Array.from({length: endAt - startAt + 1}, (_, index) => index + startAt)
  }
  
  const calculateAliquot = function(n: number) {
    return n==1? 0: range(1, n - 1).reduce((a, v) => a + (n % v == 0 ? v : 0) ) 
  }

  if (n < 1) throw new Error("Classification is only possible for natural numbers.")
  
  const aliquot = calculateAliquot(n)
  return aliquot == n ? 'perfect': (aliquot > n ? 'abundant': 'deficient')
}
