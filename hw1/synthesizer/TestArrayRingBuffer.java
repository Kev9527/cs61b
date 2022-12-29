package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void iterateTest() {
        ArrayRingBuffer<String> arb = new ArrayRingBuffer<>(3);
        assertEquals(0, arb.fillCount());
        assertEquals(3, arb.capacity());
        String str1 = "abc";
        String str2 = "gef";
        String str3 = "jjk";
        arb.enqueue(str1);
        arb.enqueue(str2);
        arb.enqueue(str3);
        for (String i : arb) {
            System.out.println(i);
        }
    }

    @Test
    public void enqueueTest() {
        ArrayRingBuffer<String> arb = new ArrayRingBuffer<>(3);
        String str1 = "abc";
        String str2 = "gef";
        String str3 = "jjk";
        String str4 = "dfde";
        arb.enqueue(str1);
        arb.enqueue(str2);
        arb.enqueue(str3);
        try{
            arb.enqueue(str4);
        } catch (RuntimeException re) {
            String reStr = "Ring buffer overflow";
            assertEquals(reStr, re.getMessage());
        }
    }
    @Test
    public void dequeueTest() {
        ArrayRingBuffer<String> arb = new ArrayRingBuffer<>(3);
        String str1 = "abc";
        String str2 = "gef";
        String str3 = "jjk";
        String str4 = "dfde";
        arb.enqueue(str1);
        arb.enqueue(str2);
        arb.enqueue(str3);
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        try{
            arb.dequeue();
        } catch (RuntimeException re) {
            String reStr = "Ring buffer underflow";
            assertEquals(reStr, re.getMessage());
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
