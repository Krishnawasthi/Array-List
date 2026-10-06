# ArrayList Interview Questions and Answers
## Java Interview Preparation – 3 to 4 Years Experience

---

# 🔹 Basic ArrayList Questions

## 1. What is ArrayList and how does it work internally?

`ArrayList` is a resizable array implementation of the `List` interface in Java.

Internally, it uses a backing array to store elements. When the array does not have enough capacity, ArrayList creates a larger array and copies the existing elements into it.

Example:

```java
List<String> list = new ArrayList<>();

list.add("Java");
list.add("Spring");
list.add("Docker");
```

Important characteristics:

- Maintains insertion order
- Allows duplicate elements
- Allows multiple `null` values
- Provides O(1) index-based access
- Automatically grows when capacity is insufficient
- Is not thread-safe by default

---

# 2. What is the difference between ArrayList's size and its internal capacity?

`size` and `capacity` are different concepts.

### Size

Size is the number of elements currently stored in the ArrayList.

```java
ArrayList<String> list = new ArrayList<>();

list.add("Java");
list.add("Spring");
list.add("Kafka");
```

Here:

```text
size = 3
```

### Capacity

Capacity is the amount of space currently available in the backing array before resizing is required.

For example:

```text
size     = 3
capacity = 10
```

Therefore:

```text
size <= capacity
```

Capacity is an internal implementation detail and is not directly exposed through the `List` interface.

---

# 3. What is the initial/default capacity of an ArrayList, and when is the internal array created?

This is a common interview trick.

When we write:

```java
ArrayList<String> list = new ArrayList<>();
```

modern Java implementations do not immediately allocate an array of 10 elements.

The no-argument constructor starts with an empty internal array. When the first element is added, the backing array is allocated.

```java
list.add("Java");
```

The commonly quoted default capacity is 10, but the important modern implementation detail is:

```text
new ArrayList<>()
        ↓
empty internal array
        ↓
first add()
        ↓
backing array is allocated
```

You can also specify an initial capacity:

```java
ArrayList<String> list = new ArrayList<>(100);
```

This is useful when you know approximately how many elements will be stored.

---

# 4. What happens internally when an ArrayList exceeds its current capacity?

Suppose the current capacity is 10 and the ArrayList already contains 10 elements.

When:

```java
list.add("New Element");
```

is executed, there is no free space.

ArrayList then:

1. Detects that capacity is insufficient.
2. Calculates a new larger capacity.
3. Creates a new larger array.
4. Copies existing elements into the new array.
5. Makes the new array the backing array.
6. Adds the new element.

Conceptually:

```text
Old array:

[A][B][C][D][E][F][G][H][I][J]

              ↓ resize

New array:

[A][B][C][D][E][F][G][H][I][J][ ][ ][ ][ ][ ]
```

The old array can become eligible for garbage collection if no other references point to it.

---

# 5. How does ArrayList grow its capacity? What is the growth factor in modern Java?

Modern Java implementations generally increase ArrayList capacity by approximately 50%.

The growth calculation is conceptually:

```java
newCapacity = oldCapacity + (oldCapacity >> 1);
```

This is approximately:

```text
new capacity ≈ old capacity × 1.5
```

For example, conceptually:

```text
10 → 15
15 → 22
22 → 33
33 → 49
```

Exact values can vary because of integer rounding and implementation details.

The important point is that ArrayList increases capacity in larger chunks instead of increasing it by only one element every time it becomes full.

---

# 6. What is the time complexity of add(), get(), set(), remove(), and contains() in ArrayList?

| Operation | Typical Complexity | Worst Case |
|---|---:|---:|
| `add(element)` | O(1) amortized | O(n) |
| `get(index)` | O(1) | O(1) |
| `set(index, element)` | O(1) | O(1) |
| `remove(index)` | O(n) | O(n) |
| `remove(object)` | O(n) | O(n) |
| `contains(object)` | O(n) | O(n) |

`add()` is O(1) amortized because most insertions do not require resizing. When resizing happens, copying elements costs O(n).

---

# 7. Why is get(index) O(1) in ArrayList?

ArrayList uses an array internally.

For:

```java
list.get(3);
```

the element at index 3 can be accessed directly.

Conceptually:

```text
Index:    0       1        2        3
        +-------+--------+--------+-------+
        | Java  | Spring | Kafka  | Redis |
        +-------+--------+--------+-------+
                                      ↑
                                    get(3)
```

There is no need to traverse previous elements.

Therefore:

```text
get(index) = O(1)
```

---

# 8. Why is inserting/removing an element from the middle of an ArrayList O(n)?

ArrayList uses an array.

When an element is removed from the middle, elements after that position must shift left.

Example:

```text
[A][B][C][D][E]
```

Remove `C`:

```text
[A][B][D][E]
```

`D` and `E` must move left.

Similarly, inserting into the middle requires elements to shift right.

Therefore, insertion/removal from the middle can require moving many elements:

```text
O(n)
```

---

# 9. What happens internally when you execute list.remove(2)?

Consider:

```java
List<String> list = new ArrayList<>();

list.add("A");
list.add("B");
list.add("C");
list.add("D");

list.remove(2);
```

Indexes are:

```text
0 → A
1 → B
2 → C
3 → D
```

Therefore `remove(2)` removes `C`.

Internally, ArrayList:

1. Finds the element at index 2.
2. Shifts elements after index 2 one position to the left.
3. Decreases the size.
4. Clears the unused final reference.

Result:

```text
[A][B][D]
```

The operation is O(n) because elements may need to be shifted.

---

# 10. What is the difference between remove(int index) and remove(Object o)?

ArrayList has two overloaded methods:

```java
remove(int index)
```

and:

```java
remove(Object o)
```

Example:

```java
List<Integer> list = new ArrayList<>();

list.add(10);
list.add(20);
list.add(30);
```

This:

```java
list.remove(1);
```

calls `remove(int index)` because `1` is an `int`.

It removes the element at index 1:

```text
20
```

But:

```java
list.remove(Integer.valueOf(20));
```

calls `remove(Object)` and removes the value `20`.

### Important interview trap

```java
list.remove(1);
```

means:

> Remove element at index 1.

While:

```java
list.remove(Integer.valueOf(1));
```

means:

> Remove the Integer value 1.

---

# 🔥 3–4 Year Level

# 11. Why is ArrayList usually preferred over LinkedList for most use cases?

ArrayList is usually preferred because most applications perform more reading and iteration than middle insertion/removal.

### Reasons

1. **Fast random access**

```text
ArrayList get(index) → O(1)
LinkedList get(index) → O(n)
```

2. **Better cache locality**

ArrayList stores elements in an array-like structure, which generally provides better CPU cache locality.

3. **Less memory overhead**

LinkedList nodes require additional references such as `previous`, `next`, and `element`.

4. **Efficient iteration**

ArrayList is generally efficient for sequential traversal.

Therefore, ArrayList is usually the better default choice for a normal List unless there is a specific reason to choose another implementation.

---

# 12. ArrayList vs LinkedList — when would you choose each one in a real project?

## ArrayList

Choose ArrayList when:

- You need fast index-based access.
- You frequently iterate over data.
- You mostly add elements at the end.
- You need a general-purpose List.
- You have more read operations than middle insertions/removals.

Example:

```java
List<Product> products = new ArrayList<>();
```

This is a common choice for a product catalog.

## LinkedList

LinkedList can be considered when:

- Operations happen frequently at the ends.
- Node-based insertion/removal behavior is useful.
- You need List/Deque functionality.

Example:

```java
Deque<String> queue = new LinkedList<>();
```

However, for queue/deque use cases, `ArrayDeque` is often preferred in modern Java.

### Important interview point

Do not simply say:

> LinkedList is faster for insertion and deletion.

That is incomplete.

Insertion/removal can be O(1) once the required node/position is already known, but finding an element by index can require O(n) traversal.

---

# 13. Is ArrayList thread-safe? If not, how can you make it thread-safe?

No. ArrayList is not thread-safe by default.

If multiple threads modify the same ArrayList concurrently without synchronization, race conditions and inconsistent behavior can occur.

One option is:

```java
List<String> list =
        Collections.synchronizedList(new ArrayList<>());
```

Another option is:

```java
CopyOnWriteArrayList<String> list =
        new CopyOnWriteArrayList<>();
```

The correct choice depends on the application's read/write pattern.

For example:

```text
Many reads + very few writes
        ↓
CopyOnWriteArrayList
```

For a synchronized wrapper around a normal List:

```text
Collections.synchronizedList()
```

may be appropriate.

---

# 14. What is the difference between Collections.synchronizedList() and CopyOnWriteArrayList?

Both provide thread-safe List operations, but they work differently.

## Collections.synchronizedList()

Example:

```java
List<String> list =
        Collections.synchronizedList(new ArrayList<>());
```

It creates a synchronized wrapper around the underlying List.

When iterating, external synchronization is generally required:

```java
synchronized (list) {
    for (String value : list) {
        System.out.println(value);
    }
}
```

It is useful when you want synchronized access to a normal List.

## CopyOnWriteArrayList

Example:

```java
CopyOnWriteArrayList<String> list =
        new CopyOnWriteArrayList<>();
```

When a modification occurs, a new copy of the underlying array is created.

Conceptually:

```text
Old array:

[A][B][C]

      ↓ add(D)

New array:

[A][B][C][D]
```

Therefore writes are relatively expensive, but reads and iteration are very suitable for read-heavy workloads.

Best suited for:

```text
Reads >> Writes
```

Examples include listener lists and read-heavy configuration data.

---

# 15. What is fail-fast behavior in ArrayList?

Fail-fast means an iterator tries to detect structural modification of an ArrayList while it is being iterated.

Example:

```java
List<String> list = new ArrayList<>();

list.add("A");
list.add("B");
list.add("C");

for (String value : list) {
    if (value.equals("B")) {
        list.remove(value);
    }
}
```

This can result in:

```text
ConcurrentModificationException
```

ArrayList maintains an internal modification count, commonly called `modCount`.

The iterator keeps an expected modification count.

If it detects an unexpected structural modification, it can throw `ConcurrentModificationException`.

### Important

Fail-fast behavior is not a thread-safety mechanism. It is mainly a mechanism for detecting incorrect modification during iteration.

---

# 16. Why does ConcurrentModificationException occur when modifying an ArrayList during iteration?

The enhanced `for` loop uses an Iterator internally.

Example:

```java
for (String value : list) {
    if (value.equals("B")) {
        list.remove(value);
    }
}
```

Conceptually:

```text
Iterator expectedModCount = 3

ArrayList modCount = 3

        ↓ list.remove()

ArrayList modCount = 4

        ↓

Iterator detects mismatch

3 != 4

        ↓

ConcurrentModificationException
```

Therefore, directly structurally modifying an ArrayList while its Iterator is being used can cause `ConcurrentModificationException`.

This can happen even in a single-threaded program.

---

# 17. How can you safely remove elements from an ArrayList while iterating?

Use the Iterator's `remove()` method.

Example:

```java
List<String> list = new ArrayList<>();

list.add("Java");
list.add("Python");
list.add("JavaScript");

Iterator<String> iterator = list.iterator();

while (iterator.hasNext()) {

    String value = iterator.next();

    if (value.equals("Python")) {
        iterator.remove();
    }
}
```

This is safe because the Iterator performs the removal and keeps its internal state consistent.

For simple filtering/removal, `removeIf()` is another good option:

```java
list.removeIf(value -> value.equals("Python"));
```

---

# 18. What is the difference between Iterator and ListIterator?

## Iterator

Iterator mainly supports forward traversal.

```text
→ → → →
```

Important methods:

```java
hasNext()
next()
remove()
```

Example:

```java
Iterator<String> iterator = list.iterator();
```

## ListIterator

ListIterator supports both forward and backward traversal.

```text
← ← ← → → →
```

Important methods:

```java
hasNext()
next()
hasPrevious()
previous()
add()
remove()
set()
```

Example:

```java
ListIterator<String> iterator = list.listIterator();
```

### Interview answer

> Iterator supports forward traversal, while ListIterator supports both forward and backward traversal and also provides add(), remove(), and set() operations.

ListIterator is available for List implementations.

---

# 19. What happens internally when you call clear() on an ArrayList?

When you call:

```java
list.clear();
```

ArrayList removes all elements from the list.

Internally, it clears the references stored in the backing array.

Before:

```text
[A][B][C][D][E]
```

After:

```text
[ ][ ][ ][ ][ ]
```

The references are cleared so the objects can become eligible for garbage collection if there are no other references to them.

The ArrayList object itself still exists.

This is valid:

```java
list.clear();
list.add("New Element");
```

### Important

`clear()` removes the elements, but it does not necessarily reduce the backing array's capacity.

Conceptually:

```text
Before:
size = 5
capacity = 10

After clear():
size = 0
capacity ≈ 10
```

---

# 20. What is the difference between clear(), removeAll(), and creating a new ArrayList?

## clear()

```java
list.clear();
```

Removes all elements from the existing ArrayList.

The same ArrayList object continues to exist.

## removeAll()

```java
list.removeAll(otherList);
```

Removes elements from the current list that are also present in another collection.

Example:

```java
List<String> list =
        new ArrayList<>(Arrays.asList("A", "B", "C"));

List<String> remove =
        new ArrayList<>(Arrays.asList("B", "C"));

list.removeAll(remove);
```

Result:

```text
[A]
```

## Creating a new ArrayList

```java
list = new ArrayList<>();
```

Creates a completely new ArrayList object and makes the variable point to it.

### Simple difference

```text
clear()
    ↓
Same object, all elements removed

removeAll()
    ↓
Matching elements removed

new ArrayList<>()
    ↓
New ArrayList object created
```

---

# 🧠 Tricky / Scenario-Based Questions

# 21. What is the difference between new ArrayList<>(list) and assigning list to another variable?

Consider:

```java
List<String> a = new ArrayList<>();

a.add("Java");
a.add("Spring");

List<String> b = a;
```

Both variables point to the same ArrayList object.

Conceptually:

```text
a ─────┐
       ↓
    ArrayList
       ↑
b ─────┘
```

Therefore:

```java
b.add("Docker");
```

also changes `a`.

Output:

```java
System.out.println(a);
```

```text
[Java, Spring, Docker]
```

## What about new ArrayList<>(a)?

```java
List<String> b = new ArrayList<>(a);
```

A new ArrayList object is created.

Conceptually:

```text
a ─────→ ArrayList A

b ─────→ ArrayList B
```

Now:

```java
b.add("Docker");
```

does not add Docker to `a`.

### Important

This is a shallow copy.

For:

```java
List<Employee> b = new ArrayList<>(a);
```

the list structure is copied, but the Employee objects themselves are not deeply cloned. Both lists contain references to the same Employee objects.

---

# 22. What happens in this case?

```java
List<Integer> list = new ArrayList<>();

list.add(10);
list.add(20);
list.add(30);

list.remove(1);
```

### What gets removed and why?

The result is:

```text
[10, 30]
```

Because:

```java
list.remove(1);
```

matches:

```java
remove(int index)
```

The `1` is interpreted as an index.

Current list:

```text
Index    Value

0        10
1        20
2        30
```

Therefore index 1 contains `20`, so `20` is removed.

## How can you remove the value 1 instead?

Use:

```java
list.remove(Integer.valueOf(1));
```

This calls:

```java
remove(Object)
```

instead of:

```java
remove(int)
```

---

# 23. Can ArrayList contain duplicate and null values? Why?

Yes.

ArrayList allows:

- Duplicate elements
- Multiple null values
- Insertion order

## Duplicate elements

```java
List<String> list = new ArrayList<>();

list.add("Java");
list.add("Java");
list.add("Spring");
```

Result:

```text
[Java, Java, Spring]
```

## Multiple null values

```java
list.add(null);
list.add(null);
```

ArrayList can contain both null values.

## Insertion order

```java
list.add("A");
list.add("C");
list.add("B");
```

Iteration produces:

```text
A
C
B
```

### Summary

| Feature | ArrayList |
|---|---|
| Duplicate elements | Yes |
| Multiple null values | Yes |
| Insertion order | Yes |
| Index-based access | Yes |

---

# 24. If you know approximately how many elements an ArrayList will contain, why might you specify an initial capacity?

Suppose an application will store approximately 10,000 employees.

Instead of:

```java
List<Employee> employees = new ArrayList<>();
```

we can write:

```java
List<Employee> employees = new ArrayList<>(10000);
```

This gives the ArrayList an initial capacity of approximately 10,000.

It can reduce the number of resizing operations.

Without an appropriate initial capacity, ArrayList may repeatedly:

```text
1. Create a larger array
2. Copy existing elements
3. Replace the old array
```

Therefore, specifying a reasonable initial capacity can improve performance during large/bulk insertions.

## Important interview point

This:

```java
new ArrayList<>(10000);
```

does NOT mean:

```text
size = 10000
```

It means approximately:

```text
size = 0
capacity = 10000
```

As elements are added, size increases:

```text
size = 1
size = 2
size = 3
...
```

### Benefits

A suitable initial capacity can:

- Reduce resizing
- Reduce array-copy operations
- Improve bulk insertion performance
- Reduce temporary array allocations

Do not blindly use a huge capacity because unnecessary capacity can waste memory.

---

# 25. Suppose your application frequently searches, inserts, and deletes data. How would you decide between ArrayList, LinkedList, HashSet, and HashMap?

There is no single collection that is best for every situation.

The choice depends on:

- Ordering
- Duplicate elements
- Searching
- Insertion
- Deletion
- Key-value requirements
- Performance
- Real-world use case

## ArrayList

```java
List<Product> products = new ArrayList<>();
```

Choose ArrayList when:

- Ordering matters
- Duplicates are allowed
- You need index-based access
- You frequently iterate
- You mostly add elements at the end

Typical performance:

```text
get(index)      → O(1)
set(index)      → O(1)
add(end)        → O(1) amortized
search          → O(n)
remove(index)   → O(n)
```

### Real-world use case

An e-commerce product catalog can use:

```java
List<Product> products = new ArrayList<>();
```

for displaying and iterating through products.

---

## LinkedList

```java
List<Product> products = new LinkedList<>();
```

LinkedList may be considered when:

- Operations occur frequently at the ends.
- Its linked-node behavior is useful.
- You need List/Deque functionality.

Typical performance:

```text
get(index)       → O(n)
search           → O(n)
addFirst()       → O(1)
addLast()        → O(1)
removeFirst()    → O(1)
removeLast()     → O(1)
```

For queue/deque requirements, `ArrayDeque` is often preferred.

---

## HashSet

```java
Set<String> emails = new HashSet<>();
```

Choose HashSet when:

- Duplicate values should not be allowed.
- Fast average lookup is required.
- Ordering is not important.

Typical average performance:

```text
add()       → O(1)
remove()    → O(1)
contains()  → O(1)
```

Example:

```java
Set<String> registeredEmails = new HashSet<>();

registeredEmails.add("abc@gmail.com");
registeredEmails.add("abc@gmail.com");
```

Only one copy is stored.

---

## HashMap

```java
Map<Integer, Product> products = new HashMap<>();
```

Choose HashMap when you need a key-value relationship.

Example:

```java
Map<Integer, Product> productsById = new HashMap<>();

productsById.put(101, product1);
productsById.put(102, product2);
```

Now:

```java
Product product = productsById.get(101);
```

can retrieve the product using its ID.

Typical average performance:

```text
put()        → O(1)
get()        → O(1)
remove()     → O(1)
containsKey  → O(1)
```

---

# Collection Selection Table

| Requirement | Recommended Collection |
|---|---|
| Ordered collection with duplicates | ArrayList |
| Fast index-based access | ArrayList |
| Frequent iteration | ArrayList |
| Unique elements | HashSet |
| Fast average lookup by value | HashSet |
| Key-value relationship | HashMap |
| Fast average lookup by key | HashMap |
| Queue / Deque | ArrayDeque |
| Frequent operations at both ends | ArrayDeque |
| Sorted unique elements | TreeSet |
| Sorted key-value data | TreeMap |

---

# Real-World E-Commerce Example

## Product List

```java
List<Product> products = new ArrayList<>();
```

Why?

- Ordering matters
- Duplicates may be allowed
- Frequent iteration
- Index-based access may be required

## Unique Categories

```java
Set<String> categories = new HashSet<>();
```

Why?

Duplicate categories are unnecessary.

## Find Product by Product ID

```java
Map<Integer, Product> productsById = new HashMap<>();
```

Why?

```text
Product ID → Product
```

Then:

```java
Product product = productsById.get(101);
```

can retrieve the product efficiently on average.

## Queue of Orders/Tasks

```java
Deque<Order> orders = new ArrayDeque<>();
```

Why?

Efficient insertion/removal from both ends.

---

# 🎯 Interview Priority

For a 3–4 year experienced Java developer, give extra attention to these topics:

## 1. Internal Working of ArrayList

Understand:

```text
ArrayList
    ↓
Backing Array
    ↓
Elements stored using indexes
    ↓
Direct index access
    ↓
O(1) get()
```

## 2. Size vs Capacity

Remember:

```text
size     = number of actual elements
capacity = space available in backing array
```

Example:

```text
size = 5
capacity = 10
```

## 3. ArrayList Resizing

When capacity is insufficient:

```text
Current array full
        ↓
Calculate new capacity
        ↓
Create larger array
        ↓
Copy elements
        ↓
Replace old array
        ↓
Add new element
```

Modern Java implementations generally grow the capacity by approximately 50%.

## 4. Time Complexity

```text
get()           → O(1)
set()           → O(1)

add(end)        → O(1) amortized

remove(index)   → O(n)
remove(object)  → O(n)

contains()      → O(n)
```

## 5. remove(int) vs remove(Object)

Very important:

```java
list.remove(1);
```

means:

```text
Remove element at index 1
```

while:

```java
list.remove(Integer.valueOf(1));
```

means:

```text
Remove the value 1
```

## 6. ArrayList vs LinkedList

Do not simply memorize:

```text
ArrayList = slow insertion
LinkedList = fast insertion
```

The real answer depends on the operation and whether the required position/node is already known.

ArrayList is generally preferred for normal List usage because of:

- O(1) random access
- Better cache locality
- Lower memory overhead
- Efficient iteration

## 7. Fail-Fast Behavior

Understand:

```text
ArrayList
    ↓
modCount
    ↓
Iterator checks modification
    ↓
Unexpected structural modification
    ↓
ConcurrentModificationException
```

Remember:

> Fail-fast behavior is not the same as thread safety.

## 8. ConcurrentModificationException

Unsafe:

```java
for (String value : list) {
    if (condition) {
        list.remove(value);
    }
}
```

Safe:

```java
Iterator<String> iterator = list.iterator();

while (iterator.hasNext()) {
    String value = iterator.next();

    if (condition) {
        iterator.remove();
    }
}
```

Or:

```java
list.removeIf(value -> condition);
```

## 9. Iterator vs ListIterator

### Iterator

```text
Forward traversal
remove()
```

### ListIterator

```text
Forward traversal
Backward traversal
add()
remove()
set()
```

## 10. ArrayList Thread Safety

ArrayList is:

```text
NOT thread-safe
```

Possible options:

```java
Collections.synchronizedList(
    new ArrayList<>()
);
```

or:

```java
CopyOnWriteArrayList
```

Choose based on the application's concurrency requirements.

## 11. Collections.synchronizedList()

Provides a synchronized wrapper around a normal List.

When iterating, external synchronization is generally required.

## 12. CopyOnWriteArrayList

Best suited for:

```text
Many reads
Few writes
```

because modifications involve creating a new copy of the underlying array.

## 13. Initial Capacity

If you know approximately how many elements will be stored:

```java
List<Employee> employees =
        new ArrayList<>(10000);
```

can reduce repeated resizing and copying.

Remember:

```text
capacity != size
```

---

# ⭐ Quick Interview Revision

### What is ArrayList?

ArrayList is a resizable array implementation of the List interface.

### Does ArrayList maintain insertion order?

Yes.

### Does ArrayList allow duplicate elements?

Yes.

### Does ArrayList allow null values?

Yes. It can contain multiple null values.

### Is ArrayList thread-safe?

No.

### What is the time complexity of get(index)?

O(1).

### What is the time complexity of contains()?

O(n).

### What is the time complexity of remove(index)?

O(n).

### Why is remove(index) O(n)?

Because elements after the removed index may need to be shifted.

### What happens when ArrayList becomes full?

It creates a larger backing array and copies the existing elements into it.

### How does ArrayList grow?

Modern Java implementations generally increase capacity by approximately 50%.

### Why is get(index) O(1)?

Because ArrayList uses an array and can directly access an element using its index.

### What is fail-fast behavior?

It means an iterator may detect unexpected structural modification of the collection and throw ConcurrentModificationException.

### Is ConcurrentModificationException only caused by multiple threads?

No. It can also occur in a single-threaded application when the collection is structurally modified incorrectly during iteration.

### How can you safely remove elements during iteration?

Use:

```java
Iterator.remove();
```

or:

```java
list.removeIf(...);
```

### What is the difference between clear() and new ArrayList<>()?

```text
clear()
    → Removes elements from the existing ArrayList.

new ArrayList<>()
    → Creates a new ArrayList object.
```

### What is the difference between:

```java
List<String> b = a;
```

and:

```java
List<String> b = new ArrayList<>(a);
```

```text
b = a
    → Both variables refer to the same object.

new ArrayList<>(a)
    → Creates a new ArrayList with copied element references.
```

### Which collection should be used for unique values?

```text
HashSet
```

### Which collection should be used for key-value data?

```text
HashMap
```

### Which collection is generally preferred for normal List usage?

```text
ArrayList
```

### Which collection is commonly preferred for Queue/Deque operations?

```text
ArrayDeque
```

---

# 🎯 Final Interview Tip

For a 3–4 year Java interview, do not just memorize:

```text
ArrayList → O(1)
LinkedList → O(n)
```

You should be able to explain WHY.

A strong interview explanation connects the internal implementation with the complexity.

```text
ArrayList
    ↓
Uses a backing array
    ↓
Elements are stored using indexes
    ↓
Direct index access
    ↓
get(index) = O(1)
```

For insertion/removal from the middle:

```text
Insert/remove in middle
        ↓
Elements after that position must shift
        ↓
O(n)
```

For resizing:

```text
Backing array becomes full
        ↓
Larger array is created
        ↓
Existing elements are copied
        ↓
Old array is replaced
        ↓
New element is added
```

Therefore:

```text
add(end)       → O(1) amortized
resize         → O(n)
get(index)     → O(1)
remove(index)  → O(n)
contains()     → O(n)
```

The key for a 3–4 year Java interview is not just knowing the answer.

You should be able to explain the **internal reason behind the answer**.
