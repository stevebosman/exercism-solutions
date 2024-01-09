export const COLORS = [
      'black',
      'brown',
      'red',
      'orange',
      'yellow',
      'green',
      'blue',
      'violet',
      'grey',
      'white',
    ]

export const METRICS = [
      '',
      'kilo',
      'mega',
      'giga',
  ]

export const colorCode = (color:string) => {
  return COLORS.indexOf(color)
}

export const countThousands = (value: number) => {
  const numberText = value.toString()
  const zeros = numberText.length - numberText.replace(/0*$/, '').length
  return Math.floor(zeros/3)
}

export function decodedResistorValue([tens, units, zeros]: [string, string, string]) {
  const ohms = decodedValue([tens, units]) * Math.pow(10, colorCode(zeros))
  const thousandCount = countThousands(ohms)
  const metric = METRICS[thousandCount]
  return `${ohms/Math.pow(1000, thousandCount)} ${metric}ohms`     
}

export function decodedValue([tens, units]: [string, string]) {
  return 10 * colorCode(tens) + colorCode(units)
}
