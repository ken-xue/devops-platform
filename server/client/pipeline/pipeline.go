package pipeline

type Graph struct {
	Edges     []string `json:"edges"`
	Endpoints []struct {
		Data struct {
			Value string `json:"value"`
		} `json:"data"`
		ID string `json:"id"`
	} `json:"endpoints"`
	Head  string `json:"head"`
	Nodes []struct {
		Data struct {
			Value string `json:"value"`
		} `json:"data,omitempty"`
		ID     string `json:"id"`
		Points struct {
			Sources []string `json:"sources"`
			Targets []string `json:"targets"`
		} `json:"points"`
		Position struct {
			Left int `json:"left"`
			Top  int `json:"top"`
		} `json:"position"`
		Data0 struct {
			Icon  string `json:"icon"`
			Value string `json:"value"`
		} `json:"data,omitempty"`
		Data1 struct {
			Icon  string `json:"icon"`
			Value string `json:"value"`
		} `json:"data,omitempty"`
	} `json:"nodes"`
}
