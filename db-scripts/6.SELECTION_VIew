USE `rooming_db`;
CREATE 
     OR REPLACE ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `selection_view` AS
    select 
        `f`.`id` AS `facility_id`,
        `f`.`description` AS `description`,
        `f`.`building_number` AS `building_number`,
        `f`.`street` AS `street`,
        `f`.`city` AS `city`,
        `f`.`country` AS `address`,
        `f`.`lon` AS `longitude`,
        `f`.`lan` AS `latitude`,
        `r`.`price` AS `price`,
        `r`.`id` AS `roomid`,
        `ra`.`profile_image` AS `profile_image`,
        `fa`.`image` AS `facility_cover_photo`,
        `b`.`id` AS `subscription_flag`,
        `sff`.`id` AS `bookmark_flag`
    from
        (((((`facility` `f`
        left join `facility_images` `fa` ON ((`f`.`id` = `fa`.`facility_id`)))
        join `room` `r`)
        join `room_advertisers` `ra`)
        left join `bookmark` `b` ON ((`f`.`id` = `b`.`facility_id`)))
        left join `seeker_favourite_facility` `sff` ON ((`f`.`id` = `sff`.`facility_id`)))
    where
        ((`r`.`facility_id` = `f`.`id`)
            and (`f`.`room_advertisor_id` = `ra`.`id`)
            and (`f`.`is_deleted` = 0)
            and (`r`.`is_deleted` = 0)
            and (`r`.`rent_available` = 1));
