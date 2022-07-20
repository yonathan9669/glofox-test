import gql from "graphql-tag";

export const myBookings = gql`
  query myBookings($user: Int!) {
    bookings: booking(where: { member_id: { _eq: $user } }) {
      id
      date
      member: user {
        name
      }
      activity {
        name
        startAt: start_at
        endAt: end_at
      }
    }
  }
`;
