{ pkgs, lib, inputs, ... }: {
  name = "backend";
  infoSections = { biepboop = [ ''backend'' ]; };

  languages = {
    python = {
      enable = true;
      package = pkgs.python312;
    };
    java = {
      enable = true;
      gradle.enable = true;
      jdk.package = pkgs.jdk21;
    };
  };

  git-hooks = {
    hooks = {
      nixpkgs-fmt.enable = true;
    };
  };
}
