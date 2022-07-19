import gql from "graphql-tag";

export const myBusinesses = gql`
  query myBusinesses($owner: Int!) {
    businesses: business(where: { owner_id: { _eq: $owner } }) {
      id
      name
      type
      address
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
      address
    }
  }
`;
