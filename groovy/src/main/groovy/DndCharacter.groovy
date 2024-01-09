class DndCharacter {
    private static Random random = new Random()

    final int strength = ability()
    final int dexterity = ability()
    final int constitution = ability()
    final int intelligence = ability()
    final int wisdom = ability()
    final int charisma = ability()
    final int hitpoints = 10 + modifier(constitution)

    def modifier(int constitution) {
        return Math.floor((constitution - 10) / 2)
    }

    def d6() {
        return random.nextInt(6) + 1
    }

    def ability() {
        return d6() + d6() + d6()
    }

}
