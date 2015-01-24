package serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.ByteBufferInput;
import com.esotericsoftware.kryo.io.ByteBufferOutput;
import com.esotericsoftware.kryo.io.ByteBufferOutputStream;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.DefaultSerializers;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import static serializer.ThriftSerializer.INS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sangdn
 */
public class KryoSerializer {

    protected Kryo _kryo = new Kryo();
    protected ByteArrayOutputStream bos = new ByteArrayOutputStream();
    protected Output os = new Output(bos);
    protected Input input = new Input();
    public static final KryoSerializer INS = new KryoSerializer();

    protected KryoSerializer() {

    }

    public void register(Class cls) {
        _kryo.register(cls, new FieldSerializer(_kryo, cls));
    }

    public byte[] serialize(Object obj) throws IOException {
        os.clear();
        _kryo.writeObject(os, obj);
        os.flush();

        return bos.toByteArray();

    }

    public <T> T deserialize(Class<T> cls, byte[] data) throws IOException {
        input.setBuffer(data);
        return _kryo.readObject(input, cls);
        
    }

}
