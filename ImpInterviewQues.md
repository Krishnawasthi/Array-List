# ArrayList Interview Questions — 3–4 Years Experience

This README contains the **25 most important ArrayList interview questions** for a Java developer with around **3–4 years of experience**.

The questions focus on:

* Internal working
* Capacity and resizing
* Time complexity
* `remove()` behavior
* Iterators
* Fail-fast behavior
* Thread safety
* ArrayList vs LinkedList
* Real-world collection selection

---

## ⭐ Must Know

### 1. What is ArrayList and how does it work internally?

### 2. What is the difference between ArrayList's `size` and its internal capacity?

### 3. What is the initial/default capacity of an ArrayList, and when is the internal array created?

### 4. What happens internally when an ArrayList exceeds its current capacity?

### 5. How does ArrayList grow its capacity? What is the growth factor in modern Java?

### 6. What is the time complexity of `add()`, `get()`, `set()`, `remove()`, and `contains()` in ArrayList?

### 7. Why is `get(index)` O(1) in ArrayList?

### 8. Why is inserting/removing an element from the middle of an ArrayList O(n)?

### 9. What happens internally when you execute `list.remove(2)`?

### 10. What is the difference between `remove(int index)` and `remove(Object o)`?

---

## 🔥 3–4 Year Level

### 11. Why is ArrayList usually preferred over LinkedList for most use cases?

### 12. ArrayList vs LinkedList — when would you choose each one in a real project?

### 13. Is ArrayList thread-safe? If not, how can you make it thread-safe?

### 14. What is the difference between `Collections.synchronizedList()` and `CopyOnWriteArrayList`?

### 15. What is fail-fast behavior in ArrayList?

### 16. Why does `ConcurrentModificationException` occur when modifying an ArrayList during iteration?

### 17. How can you safely remove elements from an ArrayList while iterating?

### 18. What is the difference between `Iterator` and `ListIterator`?

### 19. What happens internally when you call `clear()` on an ArrayList?

### 20. What is the difference between `clear()`, `removeAll()`, and creating a new ArrayList?

---

## 🧠 Tricky / Scenario-Based Questions

### 21. What is the difference between `new ArrayList<>(list)` and assigning `list` to another variable?

```java
List<String> a = new ArrayList<>();

List<String> b = a;
```

Explain what happens when you modify `b`.

---

### 22. What happens in this case?

```java
List<Integer> list = new ArrayList<>();

list.add(10);
list.add(20);
list.add(30);

list.remove(1);
```

**Question:** What gets removed and why?

---

### 23. Can ArrayList contain duplicate and `null` values? Why?

Explain how ArrayList handles:

* Duplicate elements
* Multiple `null` values
* Insertion order

---

### 24. If you know approximately how many elements an ArrayList will contain, why might you specify an initial capacity?

Example:

```java
List<Employee> employees = new ArrayList<>(10000);
```

Explain how specifying initial capacity can affect performance.

---

### 25. Suppose your application frequently searches, inserts, and deletes data. How would you decide between ArrayList, LinkedList, HashSet, and HashMap?

Explain your choice based on:

* Ordering
* Duplicate elements
* Searching
* Insertion
* Deletion
* Key-value requirements
* Performance
* Real-world use cases

---

# 🎯 Interview Priority

For a **3–4 years experienced Java developer**, give extra attention to these topics:

1. **Internal working of ArrayList**
2. **Size vs capacity**
3. **ArrayList resizing**
4. **Time complexity**
5. **`remove(int)` vs `remove(Object)`**
6. **ArrayList vs LinkedList**
7. **Fail-fast behavior**
8. **`ConcurrentModificationException`**
9. **Iterator and ListIterator**
10. **ArrayList thread safety**
11. **`Collections.synchronizedList()`**
12. **`CopyOnWriteArrayList`**
13. **Initial capacity**
14. **Real-world collection selection**

---

# 💡 Interview Tip

Don't prepare these questions only by memorizing definitions.

For a 3–4 year Java interview, try to explain each answer in this format:

**1. What is it?**

**2. How does it work internally?**

**3. What is the time complexity?**

**4. Give a small code example.**

**5. Explain a real-world use case.**

If you can explain these 25 questions confidently, you will have a strong foundation for **ArrayList-related Java interviews**.
