{ pkgs, lib, inputs, ... }: {
  name = "backend";
  infoSections = { biepboop = [ ''backend'' ]; };

  languages.java = {
    enable = true;
    gradle.enable = true;
    jdk.package = pkgs.jdk21;  # OpenJDK 21
  };

  git-hooks = {
    hooks = {
      nixpkgs-fmt.enable = true;
    };
  };
}
