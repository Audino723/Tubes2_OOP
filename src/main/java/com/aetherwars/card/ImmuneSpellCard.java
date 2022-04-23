public class ImmuneSpellCard extends SpellCard {
    public ImmuneSpellCard(ImmuneBuilder builder) {
        super(builder.name, builder.desc, Type.IMMUNE, builder.imagepath, builder.mana, builder.duration);
    }

    public void show() {
        super.show();
        System.out.println("Is Immune");
    }

    public ArrayList<Object> giveEffect() {
        ArrayList<Object> effect = new ArrayList<Object>();
        if (this.isImmune) {
            effect.add(-1);
        }
        return effect;
    }

    public static class ImmuneBuilder {
        private String name;
        private String desc;
        private String imagepath;
        private int mana;

        public ImmuneBuilder setCardName(String name) {
            this.name = name;return this;
        }
    
        public ImmuneBuilder setCardDescription(String desc) {
            this.desc = desc;return this;
        }
    
        public ImmuneBuilder setCardImagePath(String imagepath) {
            this.imagepath = imagepath; return this;
        }
    
        public ImmuneBuilder setCardMana(int mana) {
            this.mana = mana; return this;
        }

        public ImmuneSpellCard getResult() {
            return new ImmuneSpellCard(this);
        }
    }
}
