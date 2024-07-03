package org.wowyomad.questionaire.utils.patchers;

import org.springframework.stereotype.Component;
import org.wowyomad.questionaire.model.User;
import org.wowyomad.questionaire.utils.exceptions.InternalException;

import java.lang.reflect.Field;

@Component
public class UserPatcher {

    public void patch(User toPatch, User patch) {
        Field[] fields = User.class.getDeclaredFields();

        for(Field field : fields) {
            try {
                field.setAccessible(true);

                if (field.getName().equals("id") || field.getName().equals("password")) {
                    field.setAccessible(false);
                    continue;
                }

                Object value = field.get(patch);
                if(value != null) {
                    field.set(toPatch, value);
                }
                field.setAccessible(false);

            } catch (IllegalAccessException e) {
                throw new InternalException("Couldn't access or patch field of User");
            }
        }
    }
}
