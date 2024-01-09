export class DnDCharacter {
  public readonly strength = DnDCharacter.generateAbilityScore()
  public readonly dexterity = DnDCharacter.generateAbilityScore()
  public readonly constitution = DnDCharacter.generateAbilityScore()
  public readonly wisdom = DnDCharacter.generateAbilityScore()
  public readonly intelligence = DnDCharacter.generateAbilityScore()
  public readonly charisma = DnDCharacter.generateAbilityScore()
  public readonly hitpoints = 10 + DnDCharacter.getModifierFor(this.constitution)

  public static generateAbilityScore(): number {
    const rollD6 = function(): number {
      return Math.floor(Math.random() * 6) + 1
    }
    return [rollD6(), rollD6(), rollD6(), rollD6()].sort().slice(1).reduce((a, v) => a + v)
  }

  public static getModifierFor(abilityValue: number): number {
    return Math.floor((abilityValue - 10)/2)
  }
}
