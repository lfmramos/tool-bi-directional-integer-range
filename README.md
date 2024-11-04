# Bidirectional Integer Range Iterator

A Java implementation of a bidirectional integer range iterator that allows both forward and reverse iteration over a range of integers. This implementation provides a flexible way to iterate over integer ranges while supporting element removal and direction changes.

## Features

- Forward and reverse iteration over integer ranges
- Inclusive boundaries (both min and max values are included)
- Support for element removal during iteration
- Thread-safe range boundaries
- Consistent iteration order regardless of how range boundaries are specified
- Exception handling for invalid operations

## Getting Started

### Prerequisites

- Java 8 or higher
- Any Java IDE (optional)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/bidirectional-range-iterator.git
```

2. Import the project into your IDE or compile using javac:
```bash
javac io/codeforall/fanstatics/*.java
```

## Usage

### Basic Usage

```java
// Create a range from 3 to 10
Range range = new Range(3, 10);

// Forward iteration
for (Integer num : range) {
    System.out.println(num);  // Prints 3, 4, 5, 6, 7, 8, 9, 10
}

// Switch to reverse iteration
range.setInverted(true);
for (Integer num : range) {
    System.out.println(num);  // Prints 10, 9, 8, 7, 6, 5, 4, 3
}
```

### Advanced Usage

```java
Range range = new Range(1, 5);
Iterator<Integer> iterator = range.iterator();

// Using iterator methods directly
while (iterator.hasNext()) {
    Integer num = iterator.next();
    if (num % 2 == 0) {
        iterator.remove();  // Remove even numbers
    }
}
```

## Class Structure

### Range Class
The main class implementing the `Iterable<Integer>` interface. It maintains:
- Original range boundaries
- Iteration direction state
- Removed elements tracking
- Two inner iterator classes for forward and reverse iteration

### RangeTest Class
A test class demonstrating basic usage of the Range class, including:
- Forward iteration example
- Direction switching
- Reverse iteration example

## Implementation Details

- Uses boolean array to track removed elements
- Maintains immutable range boundaries
- Supports proper iterator behavior according to Java Iterator interface
- Handles edge cases and invalid operations with appropriate exceptions

## Error Handling

The implementation handles several error cases:
- Attempting to remove elements before calling next()
- Attempting to remove elements consecutively
- Attempting to get next element when no more elements exist
- Invalid range specifications

## Limitations

- Only supports integer ranges
- Memory usage grows with range size due to removal tracking
- No support for step sizes other than 1
- No support for concurrent modification

## Contributing

Contributions are welcome! Here are some ways you can contribute:
1. Report bugs
2. Suggest new features
3. Submit pull requests

## Future Improvements

- Add support for custom step sizes
- Implement more memory-efficient removal tracking
- Add support for floating-point ranges
- Add concurrent modification support
- Expand test coverage

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Authors

- Luis Felipe Ramos (@lfmramos))

## Acknowledgments

- Inspired by Python's range() function
- Built as part of CodeForAll's Java programming course
