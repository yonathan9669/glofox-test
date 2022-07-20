import gql from "graphql-tag";

export const findUser = gql`
  query findUser($name: String!) {
    users: user(where: { name: { _ilike: $name } }) {
      id
      name
      email
      phone
    }
  }
`;

export const insertUser = gql`
  mutation insertUser($name: String!, $email: String, $phone: String) {
    user: insert_user_one(
      object: { name: $name, email: $email, phone: $phone }
    ) {
      id
      name
      email
      phone
    }
  }
`;
