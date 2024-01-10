abstract class Fighter {
    boolean isVulnerable() {
        return false;
    }

    abstract int damagePoints(Fighter fighter);

    public String toString() {
        return String.format("Fighter is a %s", this.getClass().getName());
    }
}

class Warrior extends Fighter {
    private static final int STANDARD_DAMAGE = 6;
    private static final int TARGET_VULNERABLE_DAMAGE = 10;

    @Override
    int damagePoints(final Fighter fighter) {
        return fighter.isVulnerable()? TARGET_VULNERABLE_DAMAGE : STANDARD_DAMAGE;
    }
}

class Wizard extends Fighter {
    private static final int STANDARD_DAMAGE = 3;
    private static final int SPELL_DAMAGE = 12;

    private boolean spellPrepared;

    @Override
    boolean isVulnerable() {
        return !spellPrepared;
    }

    @Override
    int damagePoints(final Fighter fighter) {
        return spellPrepared ? SPELL_DAMAGE : STANDARD_DAMAGE;
    }

    void prepareSpell() {
        spellPrepared = true;
    }

}
