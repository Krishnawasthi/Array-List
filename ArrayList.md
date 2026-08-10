# ArrayList in Java

## Introduction

`ArrayList` is a **resizable array** implementation of the **List** interface provided in the `java.util` package. Unlike arrays, an `ArrayList` can grow and shrink dynamically as elements are added or removed.

It is one of the most commonly used collection classes because it provides **fast random access** using indexes and is easy to work with.

---

## Package

```java
import java.util.ArrayList;
```

---

## Declaration

```java
ArrayList<String> list = new ArrayList<>();
```

---

## Key Features

- Dynamic in size (grows automatically).
- Maintains insertion order.
- Allows duplicate elements.
- Allows multiple `null` values.
- Provides index-based access.
- Stores objects (primitive values use wrapper classes).
- Not synchronized (not thread-safe).

---
<img width="792" height="382" alt="image" src="https://github.com/user-attachments/assets/bce631c4-3c69-447e-a67a-108354f754c3" />


## Internal Working

- Internally uses a **dynamic array**.
- Default capacity is **10** (when the first element is added).
- When the array becomes full, it creates a larger array and copies all existing elements into it.

---

## Commonly Used Constructors

```java
ArrayList()
```

Creates an empty ArrayList.

```java
ArrayList(int initialCapacity)
```

Creates an ArrayList with the specified initial capacity.

```java
ArrayList(Collection<? extends E> c)
```

Creates an ArrayList containing elements of another collection.

---

# Common Methods

| Method | Description |
|---------|-------------|
| `add(E e)` | Adds an element to the end of the list. |
| `add(int index, E element)` | Inserts an element at a specific index. |
| `get(int index)` | Returns the element at the given index. |
| `set(int index, E element)` | Replaces the element at the specified index. |
| `remove(int index)` | Removes the element at the specified index. |
| `remove(Object obj)` | Removes the first occurrence of the specified object. |
| `size()` | Returns the number of elements. |
| `isEmpty()` | Checks whether the list is empty. |
| `contains(Object obj)` | Checks whether an element exists. |
| `indexOf(Object obj)` | Returns the first index of an element. |
| `lastIndexOf(Object obj)` | Returns the last index of an element. |
| `clear()` | Removes all elements from the list. |
| `clone()` | Creates a shallow copy of the ArrayList. |
| `toArray()` | Converts the ArrayList into an array. |
| `iterator()` | Returns an Iterator for traversal. |
| `listIterator()` | Returns a ListIterator for bidirectional traversal. |
| `forEach()` | Performs an action on each element. |
| `sort(Comparator c)` | Sorts the elements. |
| `replaceAll(UnaryOperator op)` | Replaces every element using a function. |
| `removeIf(Predicate p)` | Removes elements matching a condition. |
| `retainAll(Collection c)` | Keeps only common elements. |
| `removeAll(Collection c)` | Removes all matching elements. |
| `addAll(Collection c)` | Adds all elements of another collection. |
| `subList(int from, int to)` | Returns a portion of the list. |
| `ensureCapacity(int capacity)` | Increases internal capacity if required. |
| `trimToSize()` | Shrinks internal capacity to the current size. |

---

## Traversing an ArrayList

- Enhanced for loop
- Traditional for loop
- Iterator
- ListIterator
- forEach() method
- Stream API

---

## Time Complexity

| Operation | Time Complexity |
|-----------|-----------------|
| Access (`get`) | O(1) |
| Update (`set`) | O(1) |
| Add at end | O(1) (Amortized) |
| Add at middle | O(n) |
| Remove at end | O(1) |
| Remove at middle | O(n) |
| Search (`contains`) | O(n) |

---

## Advantages

- Dynamic size.
- Fast index-based access.
- Easy to use.
- Maintains insertion order.
- Rich set of built-in methods.

---

## Disadvantages

- Slow insertion and deletion in the middle.
- Consumes extra memory because of resizing.
- Not thread-safe.

---

## When to Use ArrayList

Use an `ArrayList` when:

- Frequent element access is required.
- Insertion order must be maintained.
- Duplicate elements are allowed.
- The collection size changes frequently.
- Insertions and deletions are mostly at the end.

---

## Difference Between Array and ArrayList

| Array | ArrayList |
|-------|-----------|
| Fixed size | Dynamic size |
| Stores primitives and objects | Stores objects only |
| No built-in methods | Rich built-in methods |
| Faster | Slightly slower |
| Length using `length` | Size using `size()` |

---

## Example

```java
import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) {

        ArrayList<String> fruits = new ArrayList<>();

        fruits.add("Apple");
        fruits.add("Mango");
        fruits.add("Orange");

        System.out.println(fruits);
        System.out.println(fruits.get(1));

        fruits.remove("Apple");

        System.out.println(fruits);
    }
}
```
<img width="884" height="373" alt="image" src="https://github.com/user-attachments/assets/e8a0159e-a6ac-402d-99fd-e72bcbf3083d" />

<img width="844" height="342" alt="image" src="https://github.com/user-attachments/assets/58c3755f-15d5-4534-9acc-dac944a8db1b" />

---

## Summary

- `ArrayList` is a dynamic implementation of the `List` interface.
- It internally uses a resizable array.
- It maintains insertion order and allows duplicates.
- It provides fast random access using indexes.
- It is ideal when read operations are more frequent than insertions or deletions.
- It offers many built-in methods for adding, removing, searching, sorting, and traversing elements.
