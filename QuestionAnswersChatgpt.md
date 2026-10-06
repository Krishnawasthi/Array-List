🔹 Basic ArrayList Questions

1. What is ArrayList and how does it work internally?

ArrayList is a resizable array implementation of the List interface in Java.

Internally, it uses a backing array to store elements. When the array does not have enough capacity, ArrayList creates a larger array and copies the existing elements into it.

Example:

List<String> list = new ArrayList<>();

list.add("Java");
list.add("Spring");
list.add("Docker");

Important characteristics:

Maintains insertion order

Allows duplicate elements

Allows multiple null values

Provides O(1) index-based access

Automatically grows when capacity is insufficient

Is not thread-safe by default

2. What is the difference between ArrayList's size and its internal capacity?

size and capacity are different concepts.

Size

Size is the number of elements currently stored in the ArrayList.

ArrayList<String> list = new ArrayList<>();

list.add("Java");
list.add("Spring");
list.add("Kafka");

Here:

size = 3

Capacity

Capacity is the amount of space currently available in the backing array before resizing is required.

For example:

size     = 3
capacity = 10

Therefore:

size <= capacity

Capacity is an internal implementation detail and is not directly exposed through the List interface.

3. What is the initial/default capacity of an ArrayList, and when is the internal array created?

This is a common interview trick.

When we write:

ArrayList<String> list = new ArrayList<>();

modern Java implementations do not immediately allocate an array of 10 elements.

The no-argument constructor starts with an empty internal array. When the first element is added, the backing array is allocated.

list.add("Java");

The commonly quoted default capacity is 10, but the important modern implementation detail is:

new ArrayList<>()
        ↓
empty internal array
        ↓
first add()
        ↓
backing array is allocated

You can also specify an initial capacity:

ArrayList<String> list = new ArrayList<>(100);

This is useful when you know approximately how many elements will be stored.

4. What happens internally when an ArrayList exceeds its current capacity?

Suppose the current capacity is 10 and the ArrayList already contains 10 elements.

When:

list.add("New Element");

is executed, there is no free space.

ArrayList then:

Detects that capacity is insufficient.

Calculates a new larger capacity.

Creates a new larger array.

Copies existing elements into the new array.

Makes the new array the backing array.

Adds the new element.

Conceptually:

Old array:

[A][B][C][D][E][F][G][H][I][J]

              ↓ resize

New array:

[A][B][C][D][E][F][G][H][I][J][ ][ ][ ][ ][ ]

The old array can become eligible for garbage collection if no other references point to it.

5. How does ArrayList grow its capacity? What is the growth factor in modern Java?

Modern Java implementations generally increase ArrayList capacity by approximately 50%.

The growth calculation is conceptually:

newCapacity = oldCapacity + (oldCapacity >> 1);

This is approximately:

new capacity ≈ old capacity × 1.5

For example, conceptually:

10 → 15
15 → 22
22 → 33
33 → 49

Exact values can vary because of integer rounding and implementation details.

The important point is that ArrayList increases capacity in larger chunks instead of increasing it by only one element every time it becomes full.

6. What is the time complexity of add(), get(), set(), remove(), and contains() in ArrayList?

Operation

Typical Complexity

Worst Case

add(element)

O(1) amortized

O(n)

get(index)

O(1)

O(1)

set(index, element)

O(1)

O(1)

remove(index)

O(n)

O(n)

remove(object)

O(n)

O(n)

contains(object)

O(n)

O(n)

add() is O(1) amortized because most insertions do not require resizing. When resizing happens, copying elements costs O(n).

7. Why is get(index) O(1) in ArrayList?

ArrayList uses an array internally.

For:

list.get(3);

the element at index 3 can be accessed directly.

Conceptually:

Index:    0       1        2        3
        +-------+--------+--------+-------+
        | Java  | Spring | Kafka  | Redis |
        +-------+--------+--------+-------+
                                      ↑
                                    get(3)

There is no need to traverse previous elements.

Therefore:

get(index) = O(1)

8. Why is inserting/removing an element from the middle of an ArrayList O(n)?

ArrayList uses an array.

When an element is removed from the middle, elements after that position must shift left.

Example:

[A][B][C][D][E]

Remove C:

[A][B][D][E]

D and E must move left.

Similarly, inserting into the middle requires elements to shift right.

Therefore, insertion/removal from the middle can require moving many elements:

O(n)

9. What happens internally when you execute list.remove(2)?

Consider:

List<String> list = new ArrayList<>();

list.add("A");
list.add("B");
list.add("C");
list.add("D");

list.remove(2);

Indexes are:

0 → A
1 → B
2 → C
3 → D

Therefore remove(2) removes C.

Internally, ArrayList:

Finds the element at index 2.

Shifts elements after index 2 one position to the left.

Decreases the size.

Clears the unused final reference.

Result:

[A][B][D]

The operation is O(n) because elements may need to be shifted.

10. What is the difference between remove(int index) and remove(Object o)?

ArrayList has two overloaded methods:

remove(int index)

and:

remove(Object o)

Example:

List<Integer> list = new ArrayList<>();

list.add(10);
list.add(20);
list.add(30);

This:

list.remove(1);

calls remove(int index) because 1 is an int.

It removes the element at index 1:

20

But:

list.remove(Integer.valueOf(20));

calls remove(Object) and removes the value 20.

Important interview trap

list.remove(1);

means:

Remove element at index 1.

While:

list.remove(Integer.valueOf(1));

means:

Remove the Integer value 1.

🔥 3–4 Year Level

11. Why is ArrayList usually preferred over LinkedList for most use cases?

ArrayList is usually preferred because most applications perform more reading and iteration than middle insertion/removal.

Reasons

Fast random access

ArrayList get(index) → O(1)
LinkedList get(index) → O(n)

Better cache locality

ArrayList stores elements in an array-like structure, which generally provides better CPU cache locality.

Less memory overhead

LinkedList nodes require additional references such as previous, next, and element.

Efficient iteration

ArrayList is generally efficient for sequential traversal.

Therefore, ArrayList is usually the better default choice for a normal List unless there is a specific reason to choose another implementation.

12. ArrayList vs LinkedList — when would you choose each one in a real project?

ArrayList

Choose ArrayList when:

You need fast index-based access.

You frequently iterate over data.

You mostly add elements at the end.

You need a general-purpose List.

You have more read operations than middle insertions/removals.

Example:

List<Product> products = new ArrayList<>();

This is a common choice for a product catalog.

LinkedList

LinkedList can be considered when:

Operations happen frequently at the ends.

Node-based insertion/removal behavior is useful.

You need List/Deque functionality.

Example:

Deque<String> queue = new LinkedList<>();

However, for queue/deque use cases, ArrayDeque is often preferred in modern Java.

Important interview point

Do not simply say:

LinkedList is faster for insertion and deletion.

That is incomplete.

Insertion/removal can be O(1) once the required node/position is already known, but finding an element by index can require O(n) traversal.

13. Is ArrayList thread-safe? If not, how can you make it thread-safe?

No. ArrayList is not thread-safe by default.

If multiple threads modify the same ArrayList concurrently without synchronization, race conditions and inconsistent behavior can occur.

One option is:

List<String> list =
        Collections.synchronizedList(new ArrayList<>());

Another option is:

CopyOnWriteArrayList<String> list =
        new CopyOnWriteArrayList<>();

The correct choice depends on the application's read/write pattern.

For example:

Many reads + very few writes
        ↓
CopyOnWriteArrayList

For a synchronized wrapper around a normal List:

Collections.synchronizedList()

may be appropriate.

14. What is the difference between Collections.synchronizedList() and CopyOnWriteArrayList?

Both provide thread-safe List operations, but they work differently.

Collections.synchronizedList()

Example:

List<String> list =
        Collections.synchronizedList(new ArrayList<>());

It creates a synchronized wrapper around the underlying List.

When iterating, external synchronization is generally required:

synchronized (list) {
    for (String value : list) {
        System.out.println(value);
    }
}

It is useful when you want synchronized access to a normal List.

CopyOnWriteArrayList

Example:

CopyOnWriteArrayList<String> list =
        new CopyOnWriteArrayList<>();

When a modification occurs, a new copy of the underlying array is created.

Conceptually:

Old array:

[A][B][C]

      ↓ add(D)

New array:

[A][B][C][D]

Therefore writes are relatively expensive, but reads and iteration are very suitable for read-heavy workloads.

Best suited for:

Reads >> Writes

Examples include listener lists and read-heavy configuration data.

15. What is fail-fast behavior in ArrayList?

Fail-fast means an iterator tries to detect structural modification of an ArrayList while it is being iterated.

Example:

List<String> list = new ArrayList<>();

list.add("A");
list.add("B");
list.add("C");

for (String value : list) {
    if (value.equals("B")) {
        list.remove(value);
    }
}

This can result in:

ConcurrentModificationException

ArrayList maintains an internal modification count, commonly called modCount.

The iterator keeps an expected modification count.

If it detects an unexpected structural modification, it can throw ConcurrentModificationException.

Important

Fail-fast behavior is not a thread-safety mechanism. It is mainly a mechanism for detecting incorrect modification during iteration.

16. Why does ConcurrentModificationException occur when modifying an ArrayList during iteration?

The enhanced for loop uses an Iterator internally.

Example:

for (String value : list) {
    if (value.equals("B")) {
        list.remove(value);
    }
}

Conceptually:

Iterator expectedModCount = 3

ArrayList modCount = 3

        ↓ list.remove()

ArrayList modCount = 4

        ↓

Iterator detects mismatch

3 != 4

        ↓

ConcurrentModificationException

Therefore, directly structurally modifying an ArrayList while its Iterator is being used can cause ConcurrentModificationException.

This can happen even in a single-threaded program.

17. How can you safely remove elements from an ArrayList while iterating?

Use the Iterator's remove() method.

Example:

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

This is safe because the Iterator performs the removal and keeps its internal state consistent.

For simple filtering/removal, removeIf() is another good option:

list.removeIf(value -> value.equals("Python"));

18. What is the difference between Iterator and ListIterator?

Iterator

Iterator mainly supports forward traversal.

→ → → →

Important methods:

hasNext()
next()
remove()

Example:

Iterator<String> iterator = list.iterator();

ListIterator

ListIterator supports both forward and backward traversal.

← ← ← → → →

Important methods:

hasNext()
next()
hasPrevious()
previous()
add()
remove()
set()

Example:

ListIterator<String> iterator = list.listIterator();

Interview answer

Iterator supports forward traversal, while ListIterator supports both forward and backward traversal and also provides add(), remove(), and set() operations.

ListIterator is available for List implementations.

19. What happens internally when you call clear() on an ArrayList?

When you call:

list.clear();

ArrayList removes all elements from the list.

Internally, it clears the references stored in the backing array.

Before:

[A][B][C][D][E]

After:

[ ][ ][ ][ ][ ]

The references are cleared so the objects can become eligible for garbage collection if there are no other references to them.

The ArrayList object itself still exists.

This is valid:

list.clear();
list.add("New Element");

Important

clear() removes the elements, but it does not necessarily reduce the backing array's capacity.

Conceptually:

Before:
size = 5
capacity = 10

After clear():
size = 0
capacity ≈ 10

20. What is the difference between clear(), removeAll(), and creating a new ArrayList?

clear()

list.clear();

Removes all elements from the existing ArrayList.

The same ArrayList object continues to exist.

removeAll()

list.removeAll(otherList);

Removes elements from the current list that are also present in another collection.

Example:

List<String> list =
        new ArrayList<>(Arrays.asList("A", "B", "C"));

List<String> remove =
        new ArrayList<>(Arrays.asList("B", "C"));

list.removeAll(remove);

Result:

[A]

Creating a new ArrayList

list = new ArrayList<>();

Creates a completely new ArrayList object and makes the variable point to it.

Simple difference

clear()
    ↓
Same object, all elements removed

removeAll()
    ↓
Matching elements removed

new ArrayList<>()
    ↓
New ArrayList object created

🧠 Tricky / Scenario-Based Questions

21. What is the difference between new ArrayList<>(list) and assigning list to another variable?

Consider:

List<String> a = new ArrayList<>();

a.add("Java");
a.add("Spring");

List<String> b = a;

Both variables point to the same ArrayList object.

Conceptually:

a ─────┐
       ↓
    ArrayList
       ↑
b ─────┘

Therefore:

b.add("Docker");

also changes a.

Output:

System.out.println(a);

[Java, Spring, Docker]

What about new ArrayList<>(a)?

List<String> b = new ArrayList<>(a);

A new ArrayList object is created.

Conceptually:

a ─────→ ArrayList A

b ─────→ ArrayList B

Now:

b.add("Docker");

does not add Docker to a.

Important

This is a shallow copy.

For:

List<Employee> b = new ArrayList<>(a);

the list structure is copied, but the Employee objects themselves are not deeply cloned. Both lists contain references to the same Employee objects.

22. What happens in this case?

List<Integer> list = new ArrayList<>();

list.add(10);
list.add(20);
list.add(30);

list.remove(1);

What gets removed and why?

The result is:

[10, 30]

Because:

list.remove(1);

matches:

remove(int index)

The 1 is interpreted as an index.

Current list:

Index    Value

0        10
1        20
2        30

Therefore index 1 contains 20, so 20 is removed.

How can you remove the value 1 instead?

Use:

list.remove(Integer.valueOf(1));

This calls:

remove(Object)

instead of:

remove(int)

23. Can ArrayList contain duplicate and null values? Why?

Yes.

ArrayList allows:

Duplicate elements

Multiple null values

Insertion order

Duplicate elements

List<String> list = new ArrayList<>();

list.add("Java");
list.add("Java");
list.add("Spring");

Result:

[Java, Java, Spring]

Multiple null values

list.add(null);
list.add(null);

ArrayList can contain both null values.

Insertion order

list.add("A");
list.add("C");
list.add("B");

Iteration produces:

A
C
B

Summary

Feature

ArrayList

Duplicate elements

Yes

Multiple null values

Yes

Insertion order

Yes

Index-based access

Yes

24. If you know approximately how many elements an ArrayList will contain, why might you specify an initial capacity?

Suppose an application will store approximately 10,000 employees.

Instead of:

List<Employee> employees = new ArrayList<>();

we can write:

List<Employee> employees = new ArrayList<>(10000);

This gives the ArrayList an initial capacity of approximately 10,000.

It can reduce the number of resizing operations.

Without an appropriate initial capacity, ArrayList may repeatedly:

1. Create a larger array
2. Copy existing elements
3. Replace the old array

Therefore, specifying a reasonable initial capacity can improve performance during large/bulk insertions.

Important interview point

This:

new ArrayList<>(10000);

does NOT mean:

size = 10000

It means approximately:

size = 0
capacity = 10000

As elements are added, size increases:

size = 1
size = 2
size = 3
...

Benefits

A suitable initial capacity can:

Reduce resizing

Reduce array-copy operations

Improve bulk insertion performance

Reduce temporary array allocations

Do not blindly use a huge capacity because unnecessary capacity can waste memory.

25. Suppose your application frequently searches, inserts, and deletes data. How would you decide between ArrayList, LinkedList, HashSet, and HashMap?

There is no single collection that is best for every situation.

The choice depends on:

Ordering

Duplicate elements

Searching

Insertion

Deletion

Key-value requirements

Performance

Real-world use case

ArrayList

List<Product> products = new ArrayList<>();

Choose ArrayList when:

Ordering matters

Duplicates are allowed

You need index-based access

You frequently iterate

You mostly add elements at the end

Typical performance:

get(index)      → O(1)
set(index)      → O(1)
add(end)        → O(1) amortized
search          → O(n)
remove(index)   → O(n)

Real-world use case

An e-commerce product catalog can use:

List<Product> products = new ArrayList<>();

for displaying and iterating through products.

LinkedList

List<Product> products = new LinkedList<>();

LinkedList may be considered when:

Operations occur frequently at the ends.

Its linked-node behavior is useful.

You need List/Deque functionality.

Typical performance:

get(index)       → O(n)
search           → O(n)
addFirst()       → O(1)
addLast()        → O(1)
removeFirst()    → O(1)
removeLast()     → O(1)

For queue/deque requirements, ArrayDeque is often preferred.

HashSet

Set<String> emails = new HashSet<>();

Choose HashSet when:

Duplicate values should not be allowed.

Fast average lookup is required.

Ordering is not important.

Typical average performance:

add()       → O(1)
remove()    → O(1)
contains()  → O(1)

Example:

Set<String> registeredEmails = new HashSet<>();

registeredEmails.add("abc@gmail.com");
registeredEmails.add("abc@gmail.com");

Only one copy is stored.

HashMap

Map<Integer, Product> products = new HashMap<>();

Choose HashMap when you need a key-value relationship.

Example:

Map<Integer, Product> productsById = new HashMap<>();

productsById.put(101, product1);
productsById.put(102, product2);

Now:

Product product = productsById.get(101);

can retrieve the product using its ID.

Typical average performance:

put()        → O(1)
get()        → O(1)
remove()     → O(1)
containsKey  → O(1)

Collection Selection Table

Requirement

Recommended Collection

Ordered collection with duplicates

ArrayList

Fast index-based access

ArrayList

Frequent iteration

ArrayList

Unique elements

HashSet

Fast average lookup by value

HashSet

Key-value relationship

HashMap

Fast average lookup by key

HashMap

Queue / Deque

ArrayDeque

Frequent operations at both ends

ArrayDeque

Sorted unique elements

TreeSet

Sorted key-value data

TreeMap

Real-World E-Commerce Example

Product List

List<Product> products = new ArrayList<>();

Why?

Ordering matters

Duplicates may be allowed

Frequent iteration

Index-based access may be required

Unique Categories

Set<String> categories = new HashSet<>();

Why?

Duplicate categories are unnecessary.

Find Product by Product ID

Map<Integer, Product> productsById = new HashMap<>();

Why?

Product ID → Product

Then:

Product product = productsById.get(101);

can retrieve the product efficiently on average.

Queue of Orders/Tasks

Deque<Order> orders = new ArrayDeque<>();

Why?

Efficient insertion/removal from both ends.

🎯 Interview Priority

For a 3–4 year experienced Java developer, give extra attention to these topics:

1. Internal Working of ArrayList

Understand:

ArrayList
    ↓
Backing Array
    ↓
Elements stored using indexes
    ↓
Direct index access
    ↓
O(1) get()

2. Size vs Capacity

Remember:

size     = number of actual elements
capacity = space available in backing array

Example:

size = 5
capacity = 10

3. ArrayList Resizing

When capacity is insufficient:

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

Modern Java implementations generally grow the capacity by approximately 50%.

4. Time Complexity

get()           → O(1)
set()           → O(1)

add(end)        → O(1) amortized

remove(index)   → O(n)
remove(object)  → O(n)

contains()      → O(n)

5. remove(int) vs remove(Object)

Very important:

list.remove(1);

means:

Remove element at index 1

while:

list.remove(Integer.valueOf(1));

means:

Remove the value 1

6. ArrayList vs LinkedList

Do not simply memorize:

ArrayList = slow insertion
LinkedList = fast insertion

The real answer depends on the operation and whether the required position/node is already known.

ArrayList is generally preferred for normal List usage because of:

O(1) random access

Better cache locality

Lower memory overhead

Efficient iteration

7. Fail-Fast Behavior

Understand:

ArrayList
    ↓
modCount
    ↓
Iterator checks modification
    ↓
Unexpected structural modification
    ↓
ConcurrentModificationException

Remember:

Fail-fast behavior is not the same as thread safety.

8. ConcurrentModificationException

Unsafe:

for (String value : list) {
    if (condition) {
        list.remove(value);
    }
}

Safe:

Iterator<String> iterator = list.iterator();

while (iterator.hasNext()) {
    String value = iterator.next();

    if (condition) {
        iterator.remove();
    }
}

Or:

list.removeIf(value -> condition);

9. Iterator vs ListIterator

Iterator

Forward traversal
remove()

ListIterator

Forward traversal
Backward traversal
add()
remove()
set()

10. ArrayList Thread Safety

ArrayList is:

NOT thread-safe

Possible options:

Collections.synchronizedList(
    new ArrayList<>()
);

or:

CopyOnWriteArrayList

Choose based on the application's concurrency requirements.

11. Collections.synchronizedList()

Provides a synchronized wrapper around a normal List.

When iterating, external synchronization is generally required.

12. CopyOnWriteArrayList

Best suited for:

Many reads
Few writes

because modifications involve creating a new copy of the underlying array.

13. Initial Capacity

If you know approximately how many elements will be stored:

List<Employee> employees =
        new ArrayList<>(10000);

can reduce repeated resizing and copying.

Remember:

capacity != size

⭐ Quick Interview Revision

What is ArrayList?

ArrayList is a resizable array implementation of the List interface.

Does ArrayList maintain insertion order?

Yes.

Does ArrayList allow duplicate elements?

Yes.

Does ArrayList allow null values?

Yes. It can contain multiple null values.

Is ArrayList thread-safe?

No.

What is the time complexity of get(index)?

O(1).

What is the time complexity of contains()?

O(n).

What is the time complexity of remove(index)?

O(n).

Why is remove(index) O(n)?

Because elements after the removed index may need to be shifted.

What happens when ArrayList becomes full?

It creates a larger backing array and copies the existing elements into it.

How does ArrayList grow?

Modern Java implementations generally increase capacity by approximately 50%.

Why is get(index) O(1)?

Because ArrayList uses an array and can directly access an element using its index.

What is fail-fast behavior?

It means an iterator may detect unexpected structural modification of the collection and throw ConcurrentModificationException.

Is ConcurrentModificationException only caused by multiple threads?

No. It can also occur in a single-threaded application when the collection is structurally modified incorrectly during iteration.

How can you safely remove elements during iteration?

Use:

Iterator.remove();

or:

list.removeIf(...);

What is the difference between clear() and new ArrayList<>()?

clear()
    → Removes elements from the existing ArrayList.

new ArrayList<>()
    → Creates a new ArrayList object.

What is the difference between:

List<String> b = a;

and:

List<String> b = new ArrayList<>(a);

b = a
    → Both variables refer to the same object.

new ArrayList<>(a)
    → Creates a new ArrayList with copied element references.

Which collection should be used for unique values?

HashSet

Which collection should be used for key-value data?

HashMap

Which collection is generally preferred for normal List usage?

ArrayList

Which collection is commonly preferred for Queue/Deque operations?

ArrayDeque

🎯 Final Interview Tip

For a 3–4 year Java interview, do not just memorize:

ArrayList → O(1)
LinkedList → O(n)

You should be able to explain WHY.

A strong interview explanation connects the internal implementation with the complexity.

ArrayList
    ↓
Uses a backing array
    ↓
Elements are stored using indexes
    ↓
Direct index access
    ↓
get(index) = O(1)

For insertion/removal from the middle:

Insert/remove in middle
        ↓
Elements after that position must shift
        ↓
O(n)

For resizing:

Backing array becomes full
        ↓
Larger array is created
        ↓
Existing elements are copied
        ↓
Old array is replaced
        ↓
New element is added

Therefore:

add(end)       → O(1) amortized
resize         → O(n)
get(index)     → O(1)
remove(index)  → O(n)
contains()     → O(n)
