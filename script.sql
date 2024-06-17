create table doctor_in_clinic
(
    is_active         boolean                  default true,
    price             integer not null,
    characteristic_id bigint
        unique
        constraint fkq1tk00vpav1xc03bfc7pyabga
            references characteristic,
    clinic_id         bigint  not null
        constraint fk431q3rywb50ilfa14fvyq8ryl
            references clinic,
    created_at        timestamp with time zone default CURRENT_TIMESTAMP,
    doctor_id         bigint  not null
        constraint fkowtiklw9ryppcfnxx77f3lucj
            references doctor,
    updated_at        timestamp with time zone default CURRENT_TIMESTAMP,
    balalayka         varchar(255),
    primary key (clinic_id, doctor_id)
);

alter table doctor_in_clinic
    owner to postgres;


