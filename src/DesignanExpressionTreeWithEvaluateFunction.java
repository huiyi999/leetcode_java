import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1628. Design an Expression Tree With Evaluate Function
 *
 * Given the postfix tokens of an arithmetic expression, build and return the binary expression tree that represents this expression.
 *
 * Postfix notation is a notation for writing arithmetic expressions in which the operands (numbers) appear before their operators. For example, the postfix tokens of the expression 4*(5-(7+2)) are represented in the array postfix = ["4","5","7","2","+","-","*"].
 *
 * The class Node is an interface you should use to implement the binary expression tree. The returned tree will be tested using the evaluate function, which is supposed to evaluate the tree's value. You should not remove the Node class; however, you can modify it as you wish, and you can define other classes to implement it if needed.
 *
 * A binary expression tree is a kind of binary tree used to represent arithmetic expressions. Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (numbers), and internal nodes (nodes with two children) correspond to the operators '+' (addition), '-' (subtraction), '*' (multiplication), and '/' (division).
 *
 * It's guaranteed that no subtree will yield a value that exceeds 109 in absolute value, and all the operations are valid (i.e., no division by zero).
 *
 * Follow up: Could you design the expression tree such that it is more modular? For example, is your design able to support additional operators without making changes to your existing evaluate implementation?
 */
public class DesignanExpressionTreeWithEvaluateFunction {
}

/**
 * This is the interface for the expression tree Node.
 * You should not remove it, and you can define some classes to implement it.
 */
// Polymorphism
abstract class Node {
    public abstract int evaluate();
    // define your fields here


};

class NumericNode extends Node {
    int val;

    public NumericNode(int val) {
        this.val = val;
    }

    public int evaluate() {
        return val;
    }

}

// subclass is declared abstract
// not need to provides implementations for all of the abstract methods in its parent class
abstract class OperatorNode extends Node {
    Node left, right;

    public OperatorNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
}

// With super(), the superclass no-argument constructor is called.
// With super(parameter list), the superclass constructor with a matching parameter list is called.
class Addition extends OperatorNode {

    public Addition(Node left, Node right) {
        super(left, right);
    }

    public int evaluate() {
        return this.left.evaluate() + this.right.evaluate();
    }
}

class Subtraction extends OperatorNode {

    public Subtraction(Node left, Node right) {
        super(left, right);
    }

    public int evaluate() {
        return this.left.evaluate() - this.right.evaluate();
    }
}


class Multiplication extends OperatorNode {

    public Multiplication(Node left, Node right) {
        super(left, right);
    }

    public int evaluate() {
        return this.left.evaluate() * this.right.evaluate();
    }
}

class Division extends OperatorNode {

    public Division(Node left, Node right) {
        super(left, right);
    }

    public int evaluate() {
        return this.left.evaluate() / this.right.evaluate();
    }
}


/**
 * This is the TreeBuilder class.
 * You can treat it as the driver code that takes the postinfix input
 * and returns the expression tree represnting it as a Node.
 */

class TreeBuilder {
    Node buildTree(String[] postfix) {
        Deque<Node> deque = new ArrayDeque<>();

        for (String token : postfix) {
            if (Character.isDigit(token.charAt(0))) {
                //System.out.println("digit: "+token);
                deque.add(new NumericNode(Integer.parseInt(token)));
            } else {
                Node right = deque.pollLast();
                Node left = deque.pollLast();
                Node newNode = buildNode(token, left, right);
                //System.out.println("left: "+ left.evaluate()+" right: "+right.evaluate()+" new digit: "+newNode.evaluate());
                deque.add(newNode);
            }
        }
        return deque.peek();

    }

    private Node buildNode(String operator, Node left, Node right) {
        switch (operator) {
            case "+":
                return new Addition(left, right);
            case "-":
                return new Subtraction(left, right);
            case "*":
                return new Multiplication(left, right);
            case "/":
                return new Division(left, right);
            default:
                return null;
        }
    }
};


/**
 * Your TreeBuilder object will be instantiated and called as such:
 * TreeBuilder obj = new TreeBuilder();
 * Node expTree = obj.buildTree(postfix);
 * int ans = expTree.evaluate();
 */