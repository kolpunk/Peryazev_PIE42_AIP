class ListNode {
    int value;
    ListNode next;
    
    ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}

public class SinglyLinkedList {
    private ListNode first;
    private int elementCount;
    
    public SinglyLinkedList() {
        first = null;
        elementCount = 0;
    }
    
    public void addFirst(int value) {
        ListNode newElement = new ListNode(value);
        if (first == null) {
            first = newElement;
        } else {
            newElement.next = first;
            first = newElement;
        }
        elementCount++;
    }
    
    public void addLast(int value) {
        ListNode newElement = new ListNode(value);
        if (first == null) {
            first = newElement;
        } else {
            ListNode current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newElement;
        }
        elementCount++;
    }
    
    public void removeFirst() {
        if (first != null) {
            first = first.next;
            elementCount--;
        }
    }
    
    public void removeLast() {
        if (first == null) return;
        
        if (first.next == null) {
            first = null;
        } else {
            ListNode current = first;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
        elementCount--;
    }
    
    public void remove(int value) {
        if (first == null) return;
        
        if (first.value == value) {
            first = first.next;
            elementCount--;
            return;
        }
        
        ListNode current = first;
        while (current.next != null) {
            if (current.next.value == value) {
                current.next = current.next.next;
                elementCount--;
                return;
            }
            current = current.next;
        }
    }
    
    public boolean contains(int value) {
        ListNode current = first;
        while (current != null) {
            if (current.value == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    public int size() {
        return elementCount;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public void display() {
        ListNode current = first;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }
    
    public void clear() {
        first = null;
        elementCount = 0;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Тестирование SinglyLinkedList ===\n");
        
        SinglyLinkedList myList = new SinglyLinkedList();
        
        System.out.println("Добавляем 10, 20, 30 в конец:");
        myList.addLast(10);
        myList.addLast(20);
        myList.addLast(30);
        System.out.print("Список: ");
        myList.display();
        System.out.println("Количество элементов: " + myList.size());
        
        System.out.println("\nДобавляем 5 в начало:");
        myList.addFirst(5);
        System.out.print("Список: ");
        myList.display();
        
        System.out.println("\nЕсть ли 20? " + myList.contains(20));
        System.out.println("Есть ли 99? " + myList.contains(99));
        
        System.out.println("\nУдаляем первый элемент:");
        myList.removeFirst();
        System.out.print("Список: ");
        myList.display();
        
        System.out.println("\nУдаляем последний элемент:");
        myList.removeLast();
        System.out.print("Список: ");
        myList.display();
        
        System.out.println("\nУдаляем элемент 20:");
        myList.remove(20);
        System.out.print("Список: ");
        myList.display();
        
        System.out.println("\nСписок пуст? " + myList.isEmpty());
        
        System.out.println("\nОчищаем список:");
        myList.clear();
        System.out.println("Список пуст? " + myList.isEmpty());
        System.out.println("Размер: " + myList.size());
    }
}