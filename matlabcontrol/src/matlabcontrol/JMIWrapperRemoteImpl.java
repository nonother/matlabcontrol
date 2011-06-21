package matlabcontrol;

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

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Passes method calls off to {@link JMIWrapper}.
 * <br><br>
 * Methods called on this proxy will be performed inside of the JVM that created this object. This class is only created
 * inside of the MATLAB's JVM and so {@code JMIWrapper}'s calls will be able to communicate with MATLAB.
 * <br><br>
 * These methods are documented in {@link JMIWrapper}.
 * 
 * @since 4.0.0
 * 
 * @author <a href="mailto:nonother@gmail.com">Joshua Kaplan</a>
 */
class JMIWrapperRemoteImpl extends UnicastRemoteObject implements JMIWrapperRemote
{
    private static final long serialVersionUID = 1L;
    
    public JMIWrapperRemoteImpl() throws RemoteException { }
    
    @Override
    public void setVariable(String variableName, Object value) throws MatlabInvocationException
    {
        JMIWrapper.setVariable(variableName, value);
    }
    
    @Override
    public Object getVariable(String variableName) throws MatlabInvocationException
    {
        return JMIWrapper.getVariable(variableName);
    }
    
    @Override
    public void exit() throws MatlabInvocationException
    {
        JMIWrapper.exit();
    }

    @Override
    public Object returningFeval(String command, Object[] args) throws MatlabInvocationException
    {
        return JMIWrapper.returningFeval(command, args);
    }
    
    @Override
    public Object returningFeval(String command, Object[] args, int returnCount) throws MatlabInvocationException
    {
        return JMIWrapper.returningFeval(command, args, returnCount);
    }
    
    @Override
    public Object returningEval(String command, int returnCount) throws MatlabInvocationException
    {    
        return JMIWrapper.returningEval(command, returnCount);
    }

    @Override
    public void eval(String command) throws MatlabInvocationException
    {
        JMIWrapper.eval(command);
    }

    @Override
    public void feval(String command, Object[] args) throws MatlabInvocationException
    {
        JMIWrapper.feval(command, args);
    }
    
    @Override
    public String storeObject(Object obj, boolean keepPermanently)
    {
        return JMIWrapper.storeObject(obj, keepPermanently);
    }
    
    @Override
    public void checkConnection() { }
}