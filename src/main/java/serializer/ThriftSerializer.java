/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializer;


import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;


/**
 *
 * @author sangdn
 */
public class ThriftSerializer {
    protected TBinaryProtocol.Factory _binProtocolFactory = new TBinaryProtocol.Factory();
    protected TSerializer _binSerializer = new TSerializer(_binProtocolFactory);
    protected TDeserializer _binDeserializer = new TDeserializer(_binProtocolFactory);
    public static final ThriftSerializer INS = new ThriftSerializer();
    
    
    
    public byte[] serialize(TBase obj) throws TException{
        return _binSerializer.serialize(obj);
    }
    public void deserialize(TBase base,byte[] data) throws TException{
        _binDeserializer.deserialize(base, data);
    }
}
