create table target (
    uuid UUID PRIMARY KEY ,
    firstname text,
    name text,
    phoneNumber uuid REFERENCES phonenumber(uuid)
);

create table town(uuid uuid PRIMARY KEY, postCode NUMERIC, name text);

create table address(uuid uuid PRIMARY KEY , number NUMERIC, street text, town_uuid uuid REFERENCES town(uuid));

create table phonenumber(uuid uuid PRIMARY KEY, phoneNumber text);

create table targetAddress (
  target_uuid uuid references target(uuid),

  address_uuid uuid REFERENCES address(uuid), PRIMARY KEY(target_uuid, address_uuid)
);

