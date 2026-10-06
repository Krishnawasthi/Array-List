# ArrayList Interview Questions & Answers

## Java ArrayList — 3–4 Years Experience

This README contains important `ArrayList` interview questions covering:

- Internal working
- Size vs capacity
- Resizing
- Time complexity
- ArrayList vs LinkedList
- Thread safety
- Fail-fast behavior
- Iterator
- ConcurrentModificationException
- Real-world collection selection
- Scenario-based questions

---

# 📌 Basic ArrayList Questions

## 1. What is ArrayList and how does it work internally?

`ArrayList` is a resizable implementation of the `List` interface in Java.

Internally, ArrayList uses an array (`Object[]`) to store elements.

Example:

```java
List<String> list = new ArrayList<>();

list.add("A");
list.add("B");
list.add("C");
