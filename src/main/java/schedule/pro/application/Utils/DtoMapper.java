package schedule.pro.application.Utils;

import schedule.pro.application.Entity.Clocking;
import schedule.pro.application.Entity.Dto.RequestClockingDto;

public class DtoMapper {

    public static Clocking FromDtoToClocking (RequestClockingDto clockingDto){
        Clocking clocking = Clocking.builder()
                .day(clockingDto.getDay())
                .month(clockingDto.getMonth())
                .year(clockingDto.getYear())
                .hours(clockingDto.getHours())
                .build();
        return  clocking;
    }

}
