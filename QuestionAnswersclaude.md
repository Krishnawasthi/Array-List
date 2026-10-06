# ArrayList Interview Questions & Answers (Java, 3–4 Years Experience)

## 🟢 Core Fundamentals

### 1. What is ArrayList and how does it work internally?

`ArrayList` is a resizable-array implementation of the `List` interface (`java.util`). Internally it holds:

- `Object[] elementData`: the backing array that stores the elements
- `int size`: the number of elements actually stored
- `modCount` (inherited from `AbstractList`): counts structural modifications, used for fail-fast behavior

It implements `List`, `RandomAccess`, `Cloneable` and `Serializable`. When the array is full, it allocates a bigger array and copies the old elements into it.

---

### 2. What is the difference between ArrayList's `size` and its internal capacity?

- **size**: the number of elements currently in the list (`list.size()`).
- **capacity**: the length of the internal `elementData` array. There is no public getter for it.

Capacity is always `>= size`. Example: after `new ArrayList<>(100)` and adding 3 items, `size = 3` and `capacity = 100`.

---

### 3. What is the initial/default capacity of an ArrayList, and when is the internal array created?

- `new ArrayList<>()` assigns a shared empty array (`DEFAULTCAPACITY_EMPTY_ELEMENTDATA`). The real array of **capacity 10** is created lazily on the **first `add()`**.
- `new ArrayList<>(n)` creates an array of size `n` immediately (`n = 0` uses a shared empty array).
- `new ArrayList<>(collection)` copies the collection's elements into a new array.

---

### 4. What happens internally when an ArrayList exceeds its current capacity?

When `add()` finds no free slot, it calls `grow()`:

1. Compute the new capacity (about 1.5x the old one).
2. Allocate a new `Object[]` of that size.
3. Copy all existing elements with `Arrays.copyOf` (which uses `System.arraycopy`).
4. Point `elementData` at the new array. The old array becomes garbage.

This is why a single `add()` can occasionally be O(n).

---

### 5. How does ArrayList grow its capacity? What is the growth factor in modern Java?

```java
newCapacity = oldCapacity + (oldCapacity >> 1);   // ~1.5x
```

- The growth factor is **1.5x** (Java 7 and later). Java 6 used `(old * 3) / 2 + 1`.
- Starting from the default, capacity goes 10 → 15 → 22 → 33 → 49 → 73 ...
- There is a hard upper limit near `Integer.MAX_VALUE - 8`. Beyond that, an `OutOfMemoryError` is thrown.

---

### 6. What is the time complexity of `add()`, `get()`, `set()`, `remove()`, and `contains()`?

| Operation | Complexity |
|---|---|
| `add(e)` (at end) | **O(1) amortized** (O(n) when resizing) |
| `add(index, e)` | O(n) (shifts elements right) |
| `get(index)` | **O(1)** |
| `set(index, e)` | **O(1)** |
| `remove(index)` | O(n) (O(1) if removing the last element) |
| `remove(Object)` | O(n) (search + shift) |
| `contains(o)` | O(n) (linear search using `equals`) |

---

### 7. Why is `get(index)` O(1) in ArrayList?

Because the data sits in a contiguous array. The element's memory location is computed directly from the start address plus `index * elementSize`, with no traversal. `ArrayList` only does a bounds check and returns `elementData[index]`. This is why it implements the `RandomAccess` marker interface.

---

### 8. Why is inserting/removing an element from the middle of an ArrayList O(n)?

The array must stay contiguous. Inserting at index `i` shifts every element from `i` to the end one slot to the right. Removing shifts them one slot to the left. Both use `System.arraycopy`, which is fast but still proportional to the number of elements moved.

---

### 9. What happens internally when you execute `list.remove(2)`?

1. Bounds check on index 2.
2. `modCount++`.
3. Save `oldValue = elementData[2]`.
4. `numMoved = size - 2 - 1`. If `numMoved > 0`, `System.arraycopy(elementData, 3, elementData, 2, numMoved)` shifts the tail left.
5. `elementData[--size] = null`, so the last slot is cleared and the GC can reclaim the object (no memory leak).
6. Return `oldValue`.

The capacity does **not** shrink.

---

### 10. What is the difference between `remove(int index)` and `remove(Object o)`?

| | `remove(int index)` | `remove(Object o)` |
|---|---|---|
| Removes by | Position | Value (first match using `equals`) |
| Returns | The removed element (`E`) | `boolean` (true if found) |
| If not found / bad input | Throws `IndexOutOfBoundsException` | Returns `false` |

**Gotcha with `List<Integer>`:** `list.remove(1)` removes the element at **index 1**. To remove the **value** 1, use `list.remove(Integer.valueOf(1))`.

---

## 🔥 3–4 Year Level

### 11. Why is ArrayList usually preferred over LinkedList for most use cases?

- **Cache locality:** contiguous memory means the CPU prefetches well. LinkedList nodes are scattered across the heap.
- **O(1) random access.** LinkedList's `get(i)` is O(n).
- **Lower memory overhead:** LinkedList needs a node object (item, next, prev) for every element, roughly 24 to 40 bytes extra each.
- Appending to the end is amortized O(1), the most common operation.
- In practice, even middle insertions are often faster on ArrayList for small and medium lists, because `arraycopy` is very fast and LinkedList still has to traverse to the position first.

---

### 12. ArrayList vs LinkedList: when would you choose each one in a real project?

**ArrayList:** the default choice. Use it for read-heavy workloads, index access, iteration, appending, and building lists to return from APIs.

**LinkedList:** rarely the best choice. It is justified when you:
- Frequently add/remove at **both ends** (queue/deque behavior), or
- Do many insert/remove operations at the current position through an `Iterator`/`ListIterator`, with no index lookups.

Even for queues and deques, `ArrayDeque` is usually better than `LinkedList`.

---

### 13. Is ArrayList thread-safe? If not, how can you make it thread-safe?

**No.** Concurrent writes can lose updates, corrupt `size`, throw `ArrayIndexOutOfBoundsException`, or cause `ConcurrentModificationException`.

Options:
- `Collections.synchronizedList(new ArrayList<>())`
- `CopyOnWriteArrayList` (best for read-heavy, write-rare cases)
- `Vector` (legacy, synchronized, avoid in new code)
- External locking (`synchronized` blocks or `ReentrantLock`/`ReadWriteLock`)
- Not sharing the list at all (thread confinement or immutable `List.of`)

---

### 14. What is the difference between `Collections.synchronizedList()` and `CopyOnWriteArrayList`?

| | `synchronizedList` | `CopyOnWriteArrayList` |
|---|---|---|
| Mechanism | Every method wrapped in a `synchronized` mutex | Every write copies the whole array; reads are lock-free |
| Reads | Blocked while another thread holds the lock | Fast, no locking |
| Writes | Cheap (O(1) append) | Expensive (O(n) copy per write) |
| Iteration | Must be **manually synchronized** or you risk a CME | Safe. The iterator works on a **snapshot** and never throws a CME |
| Iterator `remove()` | Supported | **Not supported** (`UnsupportedOperationException`) |
| Best for | Balanced or write-heavy use | Read-heavy, write-rare (listeners, config, whitelists) |

```java
List<String> sync = Collections.synchronizedList(new ArrayList<>());
synchronized (sync) {          // required while iterating
    for (String s : sync) { /* ... */ }
}
```

---

### 15. What is fail-fast behavior in ArrayList?

Iterators of `ArrayList` are **fail-fast**. When an iterator is created, it records `expectedModCount = modCount`. On each `next()` (and `remove()`), it checks that `modCount == expectedModCount`. If the list was structurally modified (add/remove/clear) by anything other than the iterator itself, it throws `ConcurrentModificationException` immediately instead of risking undefined behavior.

It is **best-effort**, not a guarantee, so never rely on it for program correctness. It is meant to detect bugs.

---

### 16. Why does `ConcurrentModificationException` occur when modifying an ArrayList during iteration?

```java
for (String s : list) {
    if (s.equals("b")) list.remove(s);   // CME on the next iteration
}
```

The for-each loop uses an iterator behind the scenes. `list.remove()` increments `modCount`, but the iterator's `expectedModCount` stays unchanged. On the next `next()` call the mismatch is detected and a CME is thrown.

(Classic edge case: removing the second-to-last element makes `hasNext()` return false, so no exception happens, which is why this bug can appear intermittently.)

It can happen in a **single thread** too, not only with multiple threads.

---

### 17. How can you safely remove elements from an ArrayList while iterating?

```java
// 1. Iterator.remove()
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    if (it.next().equals("b")) it.remove();
}

// 2. removeIf (Java 8+), cleanest option
list.removeIf(s -> s.equals("b"));

// 3. Reverse index loop
for (int i = list.size() - 1; i >= 0; i--) {
    if (list.get(i).equals("b")) list.remove(i);
}

// 4. Collect the survivors into a new list
list = list.stream().filter(s -> !s.equals("b")).collect(Collectors.toList());

// 5. Use CopyOnWriteArrayList (when concurrency is also a concern)
```

---

### 18. What is the difference between `Iterator` and `ListIterator`?

| | `Iterator` | `ListIterator` |
|---|---|---|
| Direction | Forward only | **Forward and backward** (`hasPrevious()`, `previous()`) |
| Applies to | Any `Collection` | `List` only |
| Modify | `remove()` only | `remove()`, **`set()`**, **`add()`** |
| Index info | None | `nextIndex()`, `previousIndex()` |
| Start position | Beginning | Any position: `list.listIterator(index)` |

---

### 19. What happens internally when you call `clear()` on an ArrayList?

```java
modCount++;
for (int i = 0; i < size; i++) elementData[i] = null;   // let the GC reclaim the objects
size = 0;
```

The internal array is **kept**, so capacity does not shrink. To release the memory, call `trimToSize()` (or create a new list).

---

### 20. What is the difference between `clear()`, `removeAll()`, and creating a new ArrayList?

| Approach | What it does |
|---|---|
| `clear()` | Removes **all** elements from the same list object. Capacity is retained. O(n) |
| `removeAll(Collection c)` | Removes only the elements **contained in `c`** (uses `c.contains`). Complexity depends on `c`. Returns `boolean` |
| `list = new ArrayList<>()` | Just points the variable at a **new** list. The old list is untouched, and any other reference to it still sees the old data. It is GC'd only when no references remain |

---

## 🧠 Tricky / Scenario-Based Questions

### 21. What is the difference between `new ArrayList<>(list)` and assigning `list` to another variable?

```java
List<String> a = new ArrayList<>();
List<String> b = a;
```

`b = a` copies only the **reference**. Both variables point to the **same object**, so modifying `b` also modifies `a`:

```java
b.add("x");
System.out.println(a);   // [x]
```

`new ArrayList<>(a)` creates a **new list** with its own backing array containing the same element references (a **shallow copy**). Adding or removing in one list does not affect the other. However, the elements themselves are shared, so mutating an element object is visible through both lists.

---

### 22. What gets removed here, and why?

```java
List<Integer> list = new ArrayList<>();
list.add(10);
list.add(20);
list.add(30);

list.remove(1);
```

**Result: `[10, 30]`. The element `20` (at index 1) is removed.**

Overload resolution picks `remove(int index)` because the literal `1` is an `int`, an exact match with no boxing needed. The compiler does not box it to `Integer` for `remove(Object)`.

To remove the value 1 instead (if it existed): `list.remove(Integer.valueOf(1));`

---

### 23. Can ArrayList contain duplicate and `null` values? Why?

Yes to both.

- **Duplicates:** allowed. `ArrayList` is just an indexed array. It does no hashing or uniqueness check (unlike `Set`).
- **Multiple `null`s:** allowed. Nothing prevents storing `null` in an array slot. `contains(null)` and `indexOf(null)` use `==` for null checks instead of `equals`.
- **Insertion order:** preserved. Elements stay at the index where they were added. `add(e)` appends at the end, and `add(i, e)` inserts at that position and shifts the rest.

---

### 24. If you know approximately how many elements an ArrayList will contain, why specify an initial capacity?

```java
List<Employee> employees = new ArrayList<>(10000);
```

Without it, the list starts at 10 and grows by about 1.5x each time it fills up: 10 → 15 → 22 → 33 → ... → 14053. That is roughly **18 resizes**, each allocating a new array and copying every element, which creates garbage for the GC. It also ends with about 4000 unused slots.

With an initial capacity of 10000, there is **one allocation and zero resizes**. This gives faster bulk inserts, less GC pressure, and more predictable latency.

Tips:
- `ensureCapacity(n)` does the same for an existing list before a bulk add.
- Don't grossly over-allocate, since unused slots waste memory. `trimToSize()` can release the excess.
- Only worth it for large lists. For small lists it is premature optimization.

---

### 25. How would you choose between ArrayList, LinkedList, HashSet, and HashMap?

| Requirement | ArrayList | LinkedList | HashSet | HashMap |
|---|---|---|---|---|
| **Ordering** | Insertion order, indexed | Insertion order | No order (`LinkedHashSet` keeps insertion order, `TreeSet` sorts) | No order (`LinkedHashMap` / `TreeMap` for order) |
| **Duplicates** | Allowed | Allowed | **Not allowed** | Keys unique, values can repeat |
| **Search (contains/get)** | O(n); get by index O(1) | O(n) | **O(1) average** | **O(1) average** by key |
| **Insertion** | O(1) amortized at end; O(n) in middle | O(1) at ends / at an iterator position | O(1) average | O(1) average |
| **Deletion** | O(n) | O(1) at ends / via iterator; finding is O(n) | O(1) average | O(1) average |
| **Key-value pairs** | No | No | No | **Yes** |
| **Memory** | Lowest | Highest per element | Medium (backed by a HashMap) | Medium to high |

**Decision guide for a workload with frequent search, insert and delete:**

- Need **fast lookup by key** (id → object, caching, indexing)? Use **HashMap**.
- Need **unique values with fast `contains`** (visited IDs, dedupe, membership checks)? Use **HashSet**.
- Need **ordered, index-based, duplicates allowed, read/iterate-heavy** data? Use **ArrayList** (the default).
- Need **queue/deque behavior** with frequent add/remove at the ends? Use **LinkedList** (or better, `ArrayDeque`).

**Real-world examples:**
- User sessions by ID: `HashMap<String, Session>`
- Unique email addresses already processed: `HashSet<String>`
- Search results to display in order: `ArrayList<Result>`
- Task queue / undo history: `ArrayDeque` / `LinkedList`
- Need ordering plus fast lookup: `LinkedHashMap` (e.g. an LRU cache)
- Need sorted keys or range queries: `TreeMap` / `TreeSet`

---

## 🎯 Interview Priority

For a 3–4 years experienced Java developer, give extra attention to these topics:

1. Internal working of ArrayList
2. Size vs capacity
3. ArrayList resizing
4. Time complexity
5. `remove(int)` vs `remove(Object)`
6. ArrayList vs LinkedList
7. Fail-fast behavior
8. `ConcurrentModificationException`
9. Iterator and ListIterator
10. ArrayList thread safety
11. `Collections.synchronizedList()`
12. `CopyOnWriteArrayList`
13. Initial capacity
14. Real-world collection selection
