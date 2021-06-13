import { GET_ERRORS, SET_CURRENT_USER } from "./types";
import axios from "axios";

export const createNewUser = (newUser, history) => async (dispatch) => {
  try {
    await axios.post("api/users/register", newUser);
    history.push("/login");
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data,
    });
  }
};
