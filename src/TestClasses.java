
public class TestClasses {
    public static void main(String[] args) {
        Creature creature = new Creature();
        BlessCurseOwner blessCurseOwner = new BlessCurseOwner(creature);
        blessCurseOwner.setMessage("HI");
        System.out.println("blessCurseOwner message:" + blessCurseOwner.getMessage());

        Scroll scroll1 = new Scroll("Avi Kak");
        scroll1.setMessage("ECE404");
        
        System.out.println("scroll message: " + scroll1.getAction());
        System.out.println("Scroll name: " + scroll1.getName());
    }
}
