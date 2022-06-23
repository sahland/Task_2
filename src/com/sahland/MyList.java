package com.sahland;

public class MyList {

    /*
    private class MyNode {

        char data;
        MyNode next;

        MyNode(char data) {
            this.data = data;
            next = null;
        }
    }

    private MyNode head;   // указатель на первый элемент

    public void push(char data) {
        MyNode newNode = new MyNode(data);
        newNode.next = head;
        head = newNode;
    }

    public void printList() {
        MyNode tNode = head;
        while (tNode != null) {
            System.out.print(tNode.data + "->");
            tNode = tNode.next;
        }
        System.out.println();
    }


    public void swap(int idx1, int idx2) {

        if (idx1 == idx2) return;

        MyNode prev_idx1 = null, curr_idx1 = head;
        while (curr_idx1 != null) {
            prev_idx1 = curr_idx1;
            curr_idx1 = curr_idx1.next;
        }

        MyNode prev_idx2 = null, curr_idx2 = head;
        while (curr_idx2 != null) {
            prev_idx2 = curr_idx2;
            curr_idx2 = curr_idx2.next;
        }

        if (curr_idx1 == null || curr_idx2 == null) return;

        if (prev_idx1 != null)
            prev_idx1.next = curr_idx2;
        else
            head = curr_idx2;

        if (prev_idx2 != null)
            prev_idx2.next = curr_idx1;
        else
            head = curr_idx1;

        MyNode temp = curr_idx1.next;
        curr_idx1.next = curr_idx2;
        curr_idx2.next = temp;

    }
     */

    private class MyNode {
        private char data;       // данные
        private MyNode next;    // указатель на следующий элемент
    }

    private MyNode first;   // указатель на первый элемент
    private MyNode last;    // указатель последний элемент
    private int size;       // размер списка

    public int size() {
        return this.size;

    } // возвращает размер списка

    public char getAt(int idx) throws Exception { // взять элемент списка по его индексу

        if (this.size == 0) {
            throw new Exception("Размер списка пустой.");
        }

        if (idx < 0 || idx >= this.size) {
            throw new Exception("Некорректный индекс.");
        }

        MyNode temp = this.first;

        for (int i = 1; i <= idx; i++) {

            temp = temp.next;

        }
        return temp.data;
    }

    private MyNode getNodeAt(int idx) throws Exception {

        if (this.size == 0) {
            throw new Exception("Размер списка пустой.");
        }

        if (idx < 0 || idx >= this.size) {
            throw new Exception("Некорректный индекс.");
        }

        MyNode temp = this.first;
        for (int i = 1; i <= idx; i++) {
            temp = temp.next;
        }

        return temp;
    } // вернуть объект по индексу

    public void addLast(char item) {


        MyNode a = new MyNode();

        a.data = item;
        a.next = null;

        if (this.size > 0)
            this.last.next = a;

        if (this.size == 0) {
            this.first = a;
            this.last = a;
            this.size += 1;
        } else {
            this.last = a;
            this.size += 1;
        }

    } // добавить в конец списка

    public int removeFirst() throws Exception {

        if (this.size == 0) {
            throw new Exception("Размер списка пустой.");
        }

        MyNode temp = this.first;

        if (this.size == 1) {
            this.first = null;
            this.last = null;
            this.size = 0;

        } else {
            this.first = this.first.next;
            this.size--;

        }

        return temp.data;

    } // удаление первого элемента из списка

    public int removeLast() throws Exception {

        if (this.size == 0) {
            throw new Exception("Размер списка пустой.");
        }

        MyNode temp = this.last;

        if (this.size == 1) {
            this.first = null;
            this.last = null;
            this.size = 0;

        } else {
            MyNode a = getNodeAt(this.size - 2);
            a.next = null;
            this.last = a;
            this.size--;

        }

        return temp.data;

    } // удаление последнего элемента из списка

    public int removeAt(int idx) throws Exception {

        if (this.size == 0) {
            throw new Exception("Размер списка пустой.");
        }

        if (idx < 0 || idx >= this.size) {
            throw new Exception("Некорректный индекс.");
        }

        if (idx == 0) {
            return removeFirst();
        } else if (idx == this.size - 1) {
            return removeLast();
        } else {

            MyNode a = getNodeAt(idx - 1);
            MyNode b = a.next;
            MyNode c = b.next;
            a.next = c;
            this.size--;
            return b.data;

        }

    } // удаление определенного элемента из списка

    public void printList() {
        MyNode temp = this.first;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.print("null");

    } // вывод элементов списка

    public void clear() {

        MyNode a = first;
        first = null;

        while (a != null) {
            MyNode b = a;
            a = a.next;
            b.next = null;
        }
    } // очистка всего списка


    public void swap(int pos) {
        MyNode prev = null;
        MyNode curr = first;
        for(int c = 1; curr != null && c < pos; c++){
            prev = curr;
            curr = curr.next;
        }
        if (pos <= 0 || curr == null || curr.next == null) {
            System.out.println("Следующий элемент null или позиция некорректна!");
        }
        else if(prev == null){ // Head case
            MyNode t = curr.next;
            curr.next = first;
            first = t;
            MyNode tmp = curr.next;
            curr.next = first.next;
            first.next = tmp;
        }
        else {
            MyNode t = curr.next;
            curr.next = prev.next;
            prev.next = t;
            MyNode tmp = curr.next.next;
            curr.next.next = prev.next.next;
            prev.next.next = tmp;
        }
    } // Метод меняющий соседний элемент списка
}