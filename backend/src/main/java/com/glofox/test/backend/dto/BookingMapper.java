package com.glofox.test.backend.dto;

import com.glofox.test.backend.entity.Activity;
import com.glofox.test.backend.entity.Booking;
import com.glofox.test.backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    Booking incoming(BookingDto bookingDto);

    BookingDto outgoing(Booking booking);

    default User idToUser(Integer id) {
        if (id == null) return null;

        User user = new User();
        user.setId(id);
        return user;
    }

    default Activity idToActivity(Integer id) {
        if (id == null) return null;

        Activity activity = new Activity();
        activity.setId(id);
        return activity;
    }

    default Integer map(User user) {
        return user.getId();
    }

    default Integer map(Activity activity) {
        return activity.getId();
    }
}
