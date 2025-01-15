public class Main {
    public static void main(String[] args) {
        DojeDoublyLinkedList list = new DojeDoublyLinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.remove((Integer)40);
        System.out.println(list.toString());
    }
}
