# EXPRESSION CONVENTION - MICHAEL MUTALE (202402427)

**A system to convert Infix to Prefix and Postfix using Stacks.**

---------------------------------------------------------------------------------------------------------------------------------

##  Project Description
This project is a technical implementation of expression transformation using the **Stack** data structure. The program serves as a utility to convert human-readable Infix notation into Postfix (Reverse Polish) and Prefix (Polish) notations. 

The primary goal is to demonstrate a deep understanding of operator precedence, stack-based memory management, and lexical tokenization.

---------------------------------------------------------------------------------------------------------------------------------

##  Technical Implementation

### 1. Workflow & Flowchart Integration
The program follows the systematic logic mapped out in the project **Flowchart**:
* **The "Decision Diamond" Logic:** Every character is passed through a type-check (Operand, Parenthesis, or Operator).
* **Process Termination:** The "Flush Stack" phase ensures all remaining operators are popped to the output.

### 2. Pseudocode Alignment
The Java code is a direct implementation of the provided **Pseudocode**:
* **Precedence Function:** Mirroring the logic to ensure mathematical accuracy.
* **The Shunting-Yard Engine:** The core loop handles the comparison between the incoming operator and the stack.

### 3. Advanced Lexical Tokenization
* **Multi-Digit Support:** Identifies integers like `100` as one unit.
* **Whitespace Neutrality:** Filters out spaces to focus purely on logic.

### 4. Prefix Conversion Strategy
Uses the **Reversal & Swap Method**:
1. Reverse Infix tokens.
2. Swap parentheses.
3. Apply Postfix engine.
4. Reverse final result.

---

## 🏗 Data Structures Used
* `java.util.Stack<String>`: Operator storage and parenthesis management.
* `java.util.List<String>`: Token storage for complex operands.
* `StringBuilder`: Efficient string reconstruction.

---


This code is structured for academic review, prioritizing readability and standard data structure principles.
