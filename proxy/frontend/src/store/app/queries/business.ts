import gql from "graphql-tag";

export const findBusinesses = gql`
  query findBusinesses($owner: Int!) {
    businesses: business(where: { owner_id: { _eq: $owner } }) {
      id
      name
      type
      address
      owner: user {
        name
        id
      }
    }
  }
`;

export const insertBusiness = gql`
  mutation insertBusiness(
    $owner: Int!
    $name: String!
    $type: business_type_enum!
    $address: String
  ) {
    business: insert_business_one(
      object: { owner_id: $owner, name: $name, type: $type, address: $address }
    ) {
      id
      name
      type
      owner_id
      address
    }
  }
`;
