class CircularNode {
    int value;
    CircularNode next;
    
    CircularNode(int value) {
        this.value = value;
        this.next = null;
    }
}

public class CircularLinkedList {
    private CircularNode head;
    private int elementCount;
    
    public CircularLinkedList() {
        head = null;
        elementCount = 0;
    }
    
    public void addFirst(int value) {
        CircularNode newElement = new CircularNode(value);
        if (head == null) {
            head = newElement;
            head.next = head;
        } else {
            CircularNode last = getLastNode();
            newElement.next = head;
            head = newElement;
            last.next = head;
        }
        elementCount++;
    }
    
    public void addLast(int value) {
        CircularNode newElement = new CircularNode(value);
        if (head == null) {
            head = newElement;
            head.next = head;
        } else {
            CircularNode last = getLastNode();
            last.next = newElement;
            newElement.next = head;
        }
        elementCount++;
    }
    
    public void removeFirst() {
        if (head == null) return;
        
        if (head.next == head) {
            head = null;
        } else {
            CircularNode last = getLastNode();
            head = head.next;
            last.next = head;
        }
        elementCount--;
    }
    
    public void removeLast() {
        if (head == null) return;
        
        if (head.next == head) {
            head = null;
        } else {
            CircularNode current = head;
            while (current.next.next != head) {
                current = current.next;
            }
            current.next = head;
        }
        elementCount--;
    }
    
    public void remove(int value) {
        if (head == null) return;
        
        if (head.value == value) {
            removeFirst();
            return;
        }
        
        CircularNode current = head;
        do {
            if (current.next.value == value) {
                current.next = current.next.next;
                elementCount--;
                return;
            }
            current = current.next;
        } while (current != head);
    }
    
    public boolean contains(int value) {
        if (head == null) return false;
        
        CircularNode current = head;
        do {
            if (current.value == value) {
                return true;
            }
            current = current.next;
        } while (current != head);
        
        return false;
    }
    
    public int size() {
        return elementCount;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void display() {
        if (head == null) {
            System.out.println();
            return;
        }
        
        CircularNode current = head;
        do {
            System.out.print(current.value + " ");
            current = current.next;
        } while (current != head);
        System.out.println();
    }
    
    public void clear() {
        head = null;
        elementCount = 0;
    }
    
    public void rotate() {
        if (head != null) {
            head = head.next;
        }
    }
    
    public boolean findCycle() {
        return head != null;
    }
    
    public boolean find(int value) {
        return contains(value);
    }
    
    public CircularLinkedList[] splitIntoTwo() {
        CircularLinkedList[] result = new CircularLinkedList[2];
        result[0] = new CircularLinkedList();
        result[1] = new CircularLinkedList();
        
        if (head == null) return result;
        
        int middle = elementCount / 2;
        CircularNode current = head;
        
        for (int i = 0; i < middle; i++) {
            result[0].addLast(current.value);
            current = current.next;
        }
        
        for (int i = middle; i < elementCount; i++) {
            result[1].addLast(current.value);
            current = current.next;
        }
        
        return result;
    }
    
    private CircularNode getLastNode() {
        if (head == null) return null;
        
        CircularNode current = head;
        while (current.next != head) {
            current = current.next;
        }
        return current;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Тестирование CircularLinkedList ===\n");
        
        CircularLinkedList myList = new CircularLinkedList();
        
        System.out.println("Добавляем 10, 20, 30 в конец:");
        myList.addLast(10);
        myList.addLast(20);
        myList.addLast(30);
        System.out.print("Список: ");
        myList.display();
        System.out.println("Размер: " + myList.size());
        
        System.out.println("\nДобавляем 5 в начало:");
        myList.addFirst(5);
        System.out.print("Список: ");
        myList.display();
        
        System.out.println("\nЕсть ли цикл? " + myList.findCycle());
        System.out.println("Есть ли 20? " + myList.find(20));
        System.out.println("Есть ли 99? " + myList.find(99));
        
        System.out.println("\nРотация списка:");
        myList.rotate();
        System.out.print("После ротации: ");
        myList.display();
        
        System.out.println("\nУдаляем первый элемент:");
        myList.removeFirst();
        System.out.print("Список: ");
        myList.display();
        
        System.out.println("\nУдаляем элемент 20:");
        myList.remove(20);
        System.out.print("Список: ");
        myList.display();
        
        System.out.println("\nРазделение списка на два:");
        myList.addLast(40);
        myList.addLast(50);
        System.out.print("Исходный список: ");
        myList.display();
        
        CircularLinkedList[] halves = myList.splitIntoTwo();
        System.out.print("Первая половина: ");
        halves[0].display();
        System.out.print("Вторая половина: ");
        halves[1].display();
        
        System.out.println("\nСписок пуст? " + myList.isEmpty());
    }
}