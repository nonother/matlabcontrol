package matlabcontrol.extensions;

/*
 * Copyright (c) 2011, Joshua Kaplan
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *  - Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 *    disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
 *    following disclaimer in the documentation and/or other materials provided with the distribution.
 *  - Neither the name of matlabcontrol nor the names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @since 4.1.0
 * 
 * @author <a href="mailto:nonother@gmail.com">Joshua Kaplan</a>
 */
public final class MatlabReturns
{
    private MatlabReturns() { }
    
    //Mappings between the number of return arguments held and the class representation
    private static final ConcurrentMap<Integer, Class<? extends MatlabReturnN>> NUM_TO_CLASS_MAP = 
            new ConcurrentHashMap<Integer, Class<? extends MatlabReturnN>>();
    private static final ConcurrentMap<Class<? extends MatlabReturnN>, Integer> CLASS_TO_NUM_MAP = 
            new ConcurrentHashMap<Class<? extends MatlabReturnN>, Integer>();
    static
    {
        Class<?>[] declaredClasses = MatlabReturns.class.getDeclaredClasses();
        for(Class<?> clazz : declaredClasses)
        {
            if(!MatlabReturnN.class.equals(clazz) && MatlabReturnN.class.isAssignableFrom(clazz))
            {
                Class<? extends MatlabReturnN> matlabReturnClass = (Class<? extends MatlabReturnN>) clazz;
                
                String name = matlabReturnClass.getName();
                Integer num = Integer.parseInt(name.substring(name.length() - 1, name.length()));
                
                NUM_TO_CLASS_MAP.put(num, matlabReturnClass);
                CLASS_TO_NUM_MAP.put(matlabReturnClass, num);
            }
        }
    }
    
    /**
     * Returns the number of return values the class represents.
     * 
     * @param clazz
     * @return 
     */
    static int getNumberOfReturns(Class<? extends MatlabReturnN> clazz)
    {
        return CLASS_TO_NUM_MAP.get(clazz);
    }
    
    /**
     * Creates a {@link MatlabReturnN} subclass which has the proper number of return arguments.
     * 
     * @param values
     * @return 
     */
    static MatlabReturnN createMatlabReturn(Object[] values)
    {
        Class<? extends MatlabReturnN> matlabReturnClass = NUM_TO_CLASS_MAP.get(values.length);
        if(matlabReturnClass == null)
        {
            throw new IllegalArgumentException("Cannot create a MatlabReturnN with length: " + values.length);
        }
        else
        {
            try
            {
                return matlabReturnClass.getDeclaredConstructor(Object[].class).newInstance(new Object[]{ values });
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException("Cannot create a MatlabReturnN with length: " + values.length, e);
            }
        }
    }
    
    /**
     * Hidden super class of all of the {@code MatlabReturn}X classes. This class is hidden in part because it is not
     * a valid return type from a method declared in an interface provided to {@link MatlabFunctionLinker}. It is
     * required that subclasses of this class be of the form {@code MatlabReturn}X where X is an integer value. This
     * format is used to interact with these classes through reflection.
     */
    static class MatlabReturnN
    {
        private final Object[] _values;
        
        MatlabReturnN(Object[] values)
        {
            _values = values;
        }
        
        Object get(int i)
        {
            return _values[i];
        }
    }
    
    public static final class MatlabReturn2<A, B> extends MatlabReturnN
    {
        MatlabReturn2(Object[] values)
        {
            super(values);
        }
        
        public A getFirst()
        {
            return (A) get(0);
        }
        
        public B getSecond()
        {
            return (B) get(1);
        }
    }
    
    public static final class MatlabReturn3<A, B, C> extends MatlabReturnN
    {
        MatlabReturn3(Object[] values)
        {
            super(values);
        }
        
        public A getFirst()
        {
            return (A) get(0);
        }
        
        public B getSecond()
        {
            return (B) get(1);
        }
        
        public C getThird()
        {
            return (C) get(2);
        }
    }
    
    public static final class MatlabReturn4<A, B, C, D> extends MatlabReturnN
    {
        MatlabReturn4(Object[] values)
        {
            super(values);
        }
        
        public A getFirst()
        {
            return (A) get(0);
        }
        
        public B getSecond()
        {
            return (B) get(1);
        }
        
        public C getThird()
        {
            return (C) get(2);
        }
        
        public D getFourth()
        {
            return (D) get(3);
        }
    }
    
    public static final class MatlabReturn5<A, B, C, D, E> extends MatlabReturnN
    {
        MatlabReturn5(Object[] values)
        {
            super(values);
        }
        
        public A getFirst()
        {
            return (A) get(0);
        }
        
        public B getSecond()
        {
            return (B) get(1);
        }
        
        public C getThird()
        {
            return (C) get(2);
        }
        
        public D getFourth()
        {
            return (D) get(3);
        }
        
        public E getFifth()
        {
            return (E) get(4);
        }
    }    
    
    public static final class MatlabReturn6<A, B, C, D, E, F> extends MatlabReturnN
    {
        MatlabReturn6(Object[] values)
        {
            super(values);
        }
        
        public A getFirst()
        {
            return (A) get(0);
        }
        
        public B getSecond()
        {
            return (B) get(1);
        }
        
        public C getThird()
        {
            return (C) get(2);
        }
        
        public D getFourth()
        {
            return (D) get(3);
        }
        
        public E getFifth()
        {
            return (E) get(4);
        }
        
        public F getSixth()
        {
            return (F) get(5);
        }
    }   
    
    public static final class MatlabReturn7<A, B, C, D, E, F, G> extends MatlabReturnN
    {
        MatlabReturn7(Object[] values)
        {
            super(values);
        }
        
        public A getFirst()
        {
            return (A) get(0);
        }
        
        public B getSecond()
        {
            return (B) get(1);
        }
        
        public C getThird()
        {
            return (C) get(2);
        }
        
        public D getFourth()
        {
            return (D) get(3);
        }
        
        public E getFifth()
        {
            return (E) get(4);
        }
        
        public F getSixth()
        {
            return (F) get(5);
        }
        
        public G getSeventh()
        {
            return (G) get(6);
        }
    }
    
    public static final class MatlabReturn8<A, B, C, D, E, F, G, H> extends MatlabReturnN
    {
        MatlabReturn8(Object[] values)
        {
            super(values);
        }
        
        public A getFirst()
        {
            return (A) get(0);
        }
        
        public B getSecond()
        {
            return (B) get(1);
        }
        
        public C getThird()
        {
            return (C) get(2);
        }
        
        public D getFourth()
        {
            return (D) get(3);
        }
        
        public E getFifth()
        {
            return (E) get(4);
        }
        
        public F getSixth()
        {
            return (F) get(5);
        }
        
        public G getSeventh()
        {
            return (G) get(6);
        }
        
        public H getEighth()
        {
            return (H) get(7);
        }
    }
    
    public static final class MatlabReturn9<A, B, C, D, E, F, G, H, I> extends MatlabReturnN
    {
        MatlabReturn9(Object[] values)
        {
            super(values);
        }
        
        public A getFirst()
        {
            return (A) get(0);
        }
        
        public B getSecond()
        {
            return (B) get(1);
        }
        
        public C getThird()
        {
            return (C) get(2);
        }
        
        public D getFourth()
        {
            return (D) get(3);
        }
        
        public E getFifth()
        {
            return (E) get(4);
        }
        
        public F getSixth()
        {
            return (F) get(5);
        }
        
        public G getSeventh()
        {
            return (G) get(6);
        }
        
        public H getEighth()
        {
            return (H) get(7);
        }
        
        public I getNinth()
        {
            return (I) get(8);
        }
    }
}