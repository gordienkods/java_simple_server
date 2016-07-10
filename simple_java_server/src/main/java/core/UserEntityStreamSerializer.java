package core;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;
import entity.UserEntity;

import java.io.IOException;

public class UserEntityStreamSerializer implements StreamSerializer<UserEntity> {

    @Override
    public int getTypeId () {
        return 1;
    }

    @Override
    public void write(ObjectDataOutput out, UserEntity userEntity )
            throws IOException {
        out.writeUTF(userEntity.toJson());
    }

    @Override
    public UserEntity read( ObjectDataInput in )
            throws IOException {
//        String surname = in.readUTF();
//        return new Employee(surname);
        System.err.println("Unrealized method");
        return null;
    }

    @Override
    public void destroy () {
    }
}
