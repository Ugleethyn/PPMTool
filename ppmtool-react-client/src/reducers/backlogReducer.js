import {
  GET_BACKLOG,
  GET_PROJECT_TASK,
  DELETE_PROJECT_TASK,
} from "../actions/types";

const initialState = {
  project_tasks: [],
  project_task: {},
};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_BACKLOG:
      return {
        ...state,
        project_tasks: action.payload,
      };
      break;
    case GET_PROJECT_TASK:
      return {
        ...state,
        project_task: action.payload,
      };
      break;

    case DELETE_PROJECT_TASK:
      return {
        ...state,
        // project_tasks: state.project_tasks.filter(
        //     (project_tasks) => project_tasks.projectSequence != action.payload
        //   ),
      };
      break;

    default:
      return state;
  }
}
