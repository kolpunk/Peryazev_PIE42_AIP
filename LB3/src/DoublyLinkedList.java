class DoubleNode {
    int value;
    DoubleNode previous;
    DoubleNode next;
    
    DoubleNode(int value) {
        this.value = value;
        this.previous = null;
        this.next = null;
    }
}

public class DoublyLinkedList {
    private DoubleNode start;
    private DoubleNode end;
    private int elementCount;
    
    public DoublyLinkedList() {
        start = null;
        end = null;
        elementCount = 0;
    }
    
    public void addFirst(int value) {
        DoubleNode newElement = new DoubleNode(value);
        if (start == null) {
            start = end = newElement;
        } else {
            newElement.next = start;
            start.previous = newElement;
            start = newElement;
        }
        elementCount++;
    }
    
    public void addLast(int value) {
        DoubleNode newElement = new DoubleNode(value);
        if (end == null) {
            start = end = newElement;
        } else {
            end.next = newElement;
            newElement.previous = end;
            end = newElement;
        }
        elementCount++;
    }
    
    public void removeFirst() {
        if (start == null) return;
        
        if (start == end) {
            start = end = null;
        } else {
            start = start.next;
            start.previous = null;
        }
        elementCount--;
    }
    
    public void removeLast() {
        if (end == null) return;
        
        if (start == end) {
            start = end = null;
        } else {
            end = end.previous;
            end.next = null;
        }
        elementCount--;
    }
    
    public void remove(int value) {
        DoubleNode current = start;
        while (current != null) {
            if (current.value == value) {
                if (current == start) {
                    removeFirst();
                } else if (current == end) {
                    removeLast();
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                    elementCount--;
                }
                return;
            }
            current = current.next;
        }
    }
    
    public boolean contains(int value) {
        DoubleNode current = start;
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
        return start == null;
    }
    
    public void display() {
        DoubleNode current = start;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }
    
    public void displayReverse() {
        DoubleNode current = end;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.previous;
        }
        System.out.println();
    }
    
    public void clear() {
        start = end = null;
        elementCount = 0;
    }
    
    public void add(int position, int value) {
        if (position < 0 || position > elementCount) {
            return;
        }
        
        if (position == 0) {
            addFirst(value);
            return;
        }
        
        if (position == elementCount) {
            addLast(value);
            return;
        }
        
        DoubleNode newElement = new DoubleNode(value);
        DoubleNode current = start;
        
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        
        newElement.previous = current.previous;
        newElement.next = current;
        current.previous.next = newElement;
        current.previous = newElement;
        
        elementCount++;
    }
    
    public void removeByIndex(int position) {
        if (position < 0 || position >= elementCount) {
            return;
        }
        
        if (position == 0) {
            removeFirst();
            return;
        }
        
        if (position == elementCount - 1) {
            removeLast();
            return;
        }
        
        DoubleNode current = start;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        
        current.previous.next = current.next;
        current.next.previous = current.previous;
        elementCount--;
    }
    
    public int get(int position) {
        if (position < 0 || position >= elementCount) {
            return -1;
        }
        
        DoubleNode current = start;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.value;
    }
    
    public int getFirst() {
        return start != null ? start.value : -1;
    }
    
    public int getLast() {
        return end != null ? end.value : -1;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Тестирование DoublyLinkedList ===\n");
        
        DoublyLinkedList myList = new DoublyLinkedList();
        
        System.out.println("Добавляем 10, 20, 30 в конец:");
        myList.addLast(10);
        myList.addLast(20);
        myList.addLast(30);
        System.out.print("Прямой порядок: ");
        myList.display();
        System.out.print("Обратный порядок: ");
        myList.displayReverse();
        System.out.println("Размер: " + myList.size());
        
        System.out.println("\nДобавляем 5 в начало:");
        myList.addFirst(5);
        System.out.print("Прямой порядок: ");
        myList.display();
        
        System.out.println("\nВставляем 15 на позицию 2:");
        myList.add(2, 15);
        System.out.print("Прямой порядок: ");
        myList.display();
        
        System.out.println("\nПервый элемент: " + myList.getFirst());
        System.out.println("Последний элемент: " + myList.getLast());
        System.out.println("Элемент на позиции 2: " + myList.get(2));
        
        System.out.println("\nЕсть ли 20? " + myList.contains(20));
        System.out.println("Есть ли 100? " + myList.contains(100));
        
        System.out.println("\nУдаляем первый элемент:");
        myList.removeFirst();
        System.out.print("Список: ");
        myList.display();
        
        System.out.println("\nУдаляем элемент на позиции 1:");
        myList.removeByIndex(1);
        System.out.print("Список: ");
        myList.display();
        
        System.out.println("\nСписок пуст? " + myList.isEmpty());
    }
}